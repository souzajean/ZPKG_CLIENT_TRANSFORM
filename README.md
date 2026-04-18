# 🚀 ZPKG_CLIENT_TRANSFORM
## SAP BTP CPI - TRANSFORM_DATE_MESSAGE_MAPPING

<br> 

## 🎯 Objetivo



<br>

## 🧩 Visão Geral da Solução



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





