# 🚢 GeoFreight Orchestrator

> **API de orquestração logística B2B** para o cálculo preciso de fretes com destino ou origem do Amapá, injetando as variáveis reais do transporte fluvial que API's de frete tradicionais ignoram.

*Desenvolvido durante o bootcamp da **Accenture** em parceria com a **Digital Innovation One (DIO)**, aplicando na prática conceitos de arquitetura de software e padrões de projeto (Facade).*

---

## 🌍 O Problema

O Amapá funciona, na prática, como uma ilha logística. Sem conexão rodoviária com as demais capitais brasileiras, o volume comercial de mercadorias entra e sai do estado majoritariamente via **transporte fluvial (balsas)**.

As APIs de cotação de frete do mercado — como o Melhor Envio — foram projetadas para a **malha rodoviária** e não contemplam as variáveis específicas da rota fluvial:

| Variável ignorada pelas APIs tradicionais | Impacto |
|---|---|
| Taxa fixa de despacho portuário | Custo fixo por embarque não contabilizado |
| Custo por kg em embarcações | Tarifa diferente da rodoviária |
| Ad valorem ajustado para risco fluvial | Seguro de carga subdimensionado |

O resultado seria um **ponto cego financeiro**: empresas e e-commerces absorvem prejuízo por cotar fretes com valores irreais.

---

## 🎯 A Solução

O **GeoFreight Orchestrator** atua como uma camada de orquestração **B2B** entre o cliente e as APIs de frete existentes.

O fluxo é esse: a API intercepta a requisição de cotação, valida a rota (garantindo que envolve o Amapá), busca os fretes base no Melhor Envio e **injeta automaticamente o custo real da balsa** no resultado — entregando ao negócio um preço total honesto para a operação.

```
Cliente (B2B)  →  GeoFreight API  →  ViaCEP (validação de rota)
                        ↓
            Melhor Envio (frete base)
                        ↓
                Cálculo da Balsa
                        ↓
     Resposta consolidada com custo total real
```

---

## 🏗 Arquitetura e Fluxo

O projeto segue uma estrutura em camadas com as seguintes responsabilidades:

```
src/main/java/me/lucaspmntl/geofreight/
├── controller/          # Entrada HTTP
├── service/             # Clientes Feign
│   └── serviceimpl/     # Orquestração de negócio
├── dto/                 # Contratos de dados
│   └── melhorenvio/     # DTOs específicos da integração Melhor Envio
├── exception/           # Exceções de domínio
├── handler/             # GlobalExceptionHandler
└── decoder/             # GlobalFeignErrorDecoder
```

**Fluxo de uma requisição:**

1. `GeoFreightController` recebe o `POST /api/v1/shippings`
2. `FreightOrquestrator` constrói o DTO para o Melhor Envio
3. `FreightOrquestrator` chama o `ViaCepService` para validar a rota (origem e destino)
4. Com a rota validada, chama o `MelhorEnvioService` para buscar os fretes base
5. Calcula o custo da balsa via `ferryPriceCalculator`
6. Agrega os valores e retorna a lista de opções com o custo total real

---

## 📡 Endpoint da API

### `POST /api/v1/shippings`

Calcula e retorna as opções de frete disponíveis para a rota informada, com o custo da balsa já incluído.

**Request Body:**

```json
{
  "cep_origin": "01001-000",
  "cep_destination": "68900-000",
  "products": [
    {
      "width": 15,
      "height": 10,
      "length": 20,
      "weight": 2.5,
      "insurance_value": 150.00,
      "quantity": 1
    }
  ]
}
```

| Campo | Tipo | Descrição |
|---|---|---|
| `cep_origin` | `String` | CEP de origem da mercadoria |
| `cep_destination` | `String` | CEP de destino da mercadoria |
| `products[].width` | `int` | Largura do produto (cm) |
| `products[].height` | `int` | Altura do produto (cm) |
| `products[].length` | `int` | Comprimento do produto (cm) |
| `products[].weight` | `double` | Peso do produto (kg) |
| `products[].insurance_value` | `double` | Valor declarado para seguro (R$) |
| `products[].quantity` | `int` | Quantidade de itens |

**Response Body (200 OK):**

```json
[
  {
    "transportName": "PAC",
    "transportCompanyPrice": 37.79,
    "ferryPrice": 42.75,
    "totalPrice": 80.54,
    "deliveryTime": 11,
    "company": {
      "name": "Correios"
    }
  },
  {
    "transportName": "SEDEX",
    "transportCompanyPrice": 46.23,
    "ferryPrice": 42.75,
    "totalPrice": 88.98,
    "deliveryTime": 6,
    "company": {
      "name": "Correios"
    }
  }
]
```

| Campo | Descrição |
|---|---|
| `transportName` | Nome da modalidade de transporte |
| `transportCompanyPrice` | Custo da transportadora |
| `ferryPrice` | Custo calculado da balsa |
| `totalPrice` | Custo total real |
| `deliveryTime` | Prazo total em dias |
| `company.name` | Nome da empresa transportadora |

---

## ⚓ Lógica de Cálculo da Balsa

O custo da balsa é calculado pelo método `ferryPriceCalculator` com base em três componentes:

```
Custo da Balsa = Taxa de Despacho + Custo por Peso + Ad Valorem
```

| Componente | Fórmula | Descrição |
|---|---|---|
| **Taxa de Despacho** | `R$ 35,00` (fixo) | Tarifa portuária por embarque |
| **Custo por Peso** | `peso_total_kg × R$ 2,50` | Tarifa por quilo nas embarcações |
| **Ad Valorem** | `valor_declarado × 1%` | Seguro ajustado ao risco fluvial |

Além do custo financeiro, o prazo de entrega é acrescido de **2 dias úteis** referentes ao trânsito fluvial.

---

## 🗺 Regras de Validação de Rota

A API valida automaticamente os CEPs informados via **ViaCEP** e aplica as seguintes regras de negócio:

| Cenário | Comportamento | Status HTTP |
|---|---|---|
| Origem **ou** destino no Amapá (AP) | ✅ Rota válida, prossegue com o cálculo | `200 OK` |
| Nenhum dos dois CEPs é do Amapá | ❌ Rejeita — serviço exclusivo para rotas com AP | `400 Bad Request` |
| Origem **e** destino no Amapá | ❌ Rejeita — rotas internas do AP não são suportadas | `400 Bad Request` |

---

## ⚠️ Tratamento de Erros

O `GlobalExceptionHandler` (`@RestControllerAdvice`) e o `GlobalFeignErrorDecoder` garantem respostas claras em qualquer cenário de falha.

**Erros de domínio (regra de negócio):**

| Exception | Causa | HTTP |
|---|---|---|
| `NonAmapaAddresException` | Rota sem envolvimento do estado do Amapá | `400` |
| `AmapaToAmapaException` | Rota interna ao Amapá (origem e destino no AP) | `400` |

**Erros de integração externa (ViaCEP / Melhor Envio):**

| Código HTTP recebido | Mensagem retornada |
|---|---|
| `400` | "Dados inválidos enviados para a integração: {API}" |
| `401` | "Falha de autenticação (Token/Chave) na integração com: {API}" |
| `404` | "Recurso não encontrado na API: {API}" |
| `500 / 503` | "A API do {API} está temporariamente fora do ar." |

---

## 🛠 Stack e Padrões de Projeto

| Tecnologia / Padrão | Uso |
|---|---|
| **Java 21** | Linguagem principal |
| **Spring Boot 4.0.3** | Framework web e IoC |
| **Spring Cloud OpenFeign** | Clientes HTTP declarativos para ViaCEP e Melhor Envio |
| **Spring Data JPA + H2 / PostgreSQL** | Persistência (H2 para desenvolvimento, PostgreSQL para produção) |
| **Lombok** | Redução de boilerplate |
| **Records (DTOs)** | Contratos de dados imutáveis e concisos |
| **`@RestControllerAdvice`** | Tratamento global de exceções |
| **`ErrorDecoder` (Feign)** | Tratamento centralizado de erros de APIs externas |
| **Facade** *(em implementação)* | Ocultar a complexidade da integração com múltiplas APIs de frete e CEP |

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos

- Java 21+
- Maven (ou use o wrapper `./mvnw` incluso)
- Conta ativa no **Melhor Envio** (ambiente de produção)

### 1. Configuração do Token

Esta API integra diretamente com o ambiente **oficial (produção)** do Melhor Envio.

1. Acesse sua conta em [melhorenvio.com.br](https://melhorenvio.com.br)
2. Gere um **Token de Acesso** no painel do usuário
3. Crie o arquivo `src/main/resources/application-prod.yml` (já ignorado pelo `.gitignore`) com:

```yaml
melhor-envio:
  token: SEU_TOKEN_DE_PRODUCAO_AQUI
  email: seu-email@cadastrado-no-melhor-envio.com
```

> ⚠️ **Atenção:** Como a integração usa o ambiente de produção, tenha cuidado ao escalar a aplicação para funcionalidades que gerem etiquetas reais.

### 2. Execução

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/geofreight-orchestrator.git

# Acesse a pasta do projeto
cd geofreight-orchestrator

# Compile e execute
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 🧪 Testes

O projeto conta com uma suíte de testes que cobre as três camadas principais da aplicação, utilizando **WireMock** para simular as integrações externas e **Mockito** para testes unitários da orquestração.

Para rodar os testes:

```bash
./mvnw test
```

**Cobertura atual:**

| Classe de Teste | Abordagem | Casos cobertos |
|---|---|---|
| `FreightOrquestratorTest` | Mockito (unitário) | Cálculo com balsa, `AmapaToAmapaException`, `NonAmapaAddresException` |
| `MelhorEnvioServiceTest` | WireMock (integração) | Deserialização da resposta com múltiplas transportadoras |
| `ViaCepServiceTest` | WireMock (integração) | Busca e mapeamento de endereço por CEP |

O perfil `test` (`application-test.yml`) redireciona todas as chamadas para o WireMock local na porta `8089`, garantindo que os testes sejam isolados e não dependam de conectividade externa.

---

## 🚀 Próximos Passos

- [ ] **Deploy em cloud** — publicar a API no Render, Railway ou AWS com URL pública
- [ ] **Documentação Swagger** — expor os endpoints via Springdoc/OpenAPI para testes direto no navegador

---

## 👨‍💻 Autor

**Lucas Santos Pimentel**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://linkedin.com/in/seu-usuario)
[![GitHub](https://img.shields.io/badge/GitHub-black?style=flat&logo=github)](https://github.com/seu-usuario)
