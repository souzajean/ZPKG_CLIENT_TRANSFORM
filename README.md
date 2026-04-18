# 🚀 ZPKG_CLIENT_TRANSFORM  
### SAP BTP CPI | Transformação de Payload com Message Mapping + Groovy

Este projeto demonstra a construção de um iFlow no SAP BTP CPI baseado em um cenário real de integração, envolvendo transformação de dados, cálculo dinâmico de idade e enriquecimento de payload.

A solução integra componentes como HTTPS Sender, Content Modifier, JSON to XML Converter, Groovy Script e Message Mapping para processar e estruturar dados de forma eficiente.

---

## 🎯 Objetivo

Neste artigo, demonstro a construção de um iFlow no SAP BTP CPI baseado em um cenário real de integração, envolvendo cálculo de idade, transformação de payload e geração de timestamp.

A solução apresenta um fluxo completo de transformação de dados, integrando componentes como HTTPS Sender, Content Modifier, JSON to XML Converter, Groovy Script e Message Mapping.

O cenário contempla:
- Cálculo dinâmico de idade utilizando Groovy  
- Enriquecimento da mensagem por meio de Exchange Properties  
- Geração de timestamp em tempo de execução  
- Estruturação de saída em XML padronizado para consumo downstream  

Tudo isso simulando um fluxo real de integração, com aplicação de boas práticas de desenvolvimento no SAP BTP CPI.

<br>

![Fluxo](imagens/capa-linkedin.png)

---

<br>

# 🏗️ 🔧 Arquitetura do iFlow

<br><br>

# 🔄 1. Fluxo da Integração

<br>

### 🧱 Criando o Package
![Fluxo](imagens/Screenshot_1.png)

<br><br>

### 🏷️ Nome do Package
```
ZPKG_CLIENT_TRANSFORM
```
![Fluxo](imagens/Screenshot_2.png)

<br>

### ➕ Adicionando o Artefato
![Fluxo](imagens/Screenshot_3.png)

<br>

### 🏷️ Nome do iFlow
```
IFL_CLIENT_TRANSFORM
```
![Fluxo](imagens/Screenshot_4.png)

<br>

### ➕ Adicionando o Adapter
![Fluxo](imagens/Screenshot_5.png)


# 🔹 2. HTTPS Sender (Trigger)
```
Endpoint: /demo/client-transform
```
![Fluxo](imagens/Screenshot_6.png)

# 🔹 3. Content Modifier

### ➕ Adicionando o Content Modifier
![Fluxo](imagens/Screenshot_7.png)

<br>

### 🏷️ Renomeando o Content Modifier
```
Nome: CM_PayloadOriginal
```
![Fluxo](imagens/Screenshot_8.png)


<br>

### ⚙️ Configuração do Content Modifier
Message Header
```
|  Action  |      Name      |  Source Type  |  Source Value    |  Data Type  |
| -------- | -------------- | ------------- | ---------------- | ----------- |
|  Create  |  Content-Type  |    Constant   |  application/xml |             |
```
![Fluxo](imagens/Screenshot_9.png)

<br>

### ⚙️ Configuração do Content Modifier
Exchange Property
```
| Action  | Name            | Source Type | Source Value    |     Data Type    |
| --------| --------------- | ----------- | --------------- | ---------------- |
|  Create |   _original	    | Expression  |    ${body}      | java.lang.String | 
```
![Fluxo](imagens/Screenshot_10.png)

<br>


### ⚙️ Configuração do Content Modifier
Body
```
Type: Expression
Body: ${body}
```
![Fluxo](imagens/Screenshot_11.png)

<br>

# 🔹 4. JSON to XML Converter

➕ Adicionando JSON to XML Converter
![Fluxo](imagens/Screenshot_12.png)

<br>

### 🏷️ Renomeando o JSON to XML Converter
```
Nome: JSON to XML
```
![Fluxo](imagens/Screenshot_13.png)

<br>

### ⚙️ Configuração JSON to XML Converter
Processing
```
Use Namespace Mapping: Desmarcar
Add XML Root Element: Desmarcar
```
![Fluxo](imagens/Screenshot_14.png)

<br>

# 🔹 5. Groovy Script

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_15.png)

<br>

### 🏷️ Renomeando o Groovy Script
![Fluxo](imagens/Screenshot_16.png)
```
GS_calculateAge
```
<br>

### ⚙️ Adicionado o Groovy Script
![Fluxo](imagens/Screenshot_17.png)

<br>

### ⚙️ Selecionando o Groovy Script
![Fluxo](imagens/Screenshot_18.png)

<br>

### ⚙️ Código Groovy Script
![Fluxo](imagens/Screenshot_19.png)
```
import com.sap.gateway.ip.core.customdev.util.Message
import java.time.LocalDate
import java.time.Period

def Message processData(Message message) {

    def body = message.getBody(String)

    // Extrai a data do XML (forma simples)
    def birthDate = body =~ /<birthDate>(.*?)<\/birthDate>/
    birthDate = birthDate ? birthDate[0][1] : null

    def age = calculateAge(birthDate)

    // Exemplo: adiciona no header ou property
    message.setProperty("age", age)

    return message
}

def String calculateAge(String birthDate) {

    if (!birthDate) return "0"

    def birth = LocalDate.parse(birthDate)
    def now   = LocalDate.now()

    def age = Period.between(birth, now).getYears()

    return age.toString()
}
```

<br>

# 🔹 6. Message Mapping

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_20.png)

<br>

### 🏷️ Renomeando o Message Mapping
![Fluxo](imagens/Screenshot_21.png)
```
MM_TransformPayload
```
<br>

### ➕ Adicionando Groovy Script
![Fluxo](imagens/Screenshot_22.png)

<br>

### ➕ Criar Message Mapping
![Fluxo](imagens/Screenshot_23.png)
```
mm_req
```

<br>

### ➕ Adicionando Source.xsd
![Fluxo](imagens/Screenshot_24.png)

<br>

### ➕ Adicionando Target.xsd
![Fluxo](imagens/Screenshot_25.png)

<br>

### 📂 Mapeando o Source.xsd no Target.xsd
![Fluxo](imagens/Screenshot_26.png)

<br>

<br>

### 📂 Configuração do mapeanto entre Source.xsd e Target.xsd
![Fluxo](imagens/Screenshot_27.png)

<br>

### 📂 Configuração do processedTimestamp
Em Function - Selecionar Date
![Fluxo](imagens/Screenshot_28.png)

<br>

### ➕ Adicionando Date
currentDate
![Fluxo](imagens/Screenshot_29.png)

<br>

### ➕ Conectar currentDate em processedTimestamp
currentDate
![Fluxo](imagens/Screenshot_30.png)

<br>

<br>

### ➕ Conectar birthDate em age
![Fluxo](imagens/Screenshot_31.png)

<br>

<br>

### ➕ Conectar Groovy Script
![Fluxo](imagens/Screenshot_32.png)

<br>

### ➕ Selecionar o calculateAge
![Fluxo](imagens/Screenshot_33.png)

<br>

<br>

### ➕ Conectado o processo
Mapeamento nos iremos conectar
```` birthDate -> calculateAge -> Age ````   
![Fluxo](imagens/Screenshot_34.png)

<br>

# 🔹 7. Postman
### ⚙️ Configuração do Postman
![Fluxo](imagens/Screenshot_35.png)

<br>

### ⚙️ Resultado no Postman
![Fluxo](imagens/Screenshot_36.png)

<br>
<br>

---

## 📦 Exemplo prático – iFlow para baixar

📦 [Download do iFlow – CPI_ZPKG_CLIENT_TRANSFORM](https://github.com/souzajean/ZPKG_CLIENT_TRANSFORM/raw/main/Package/IFL_CLIENT_TRANSFORM.zip)






















