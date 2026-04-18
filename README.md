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
|  Action  |  Name    |  Source Type  |  Source Value  |  Data Type  |
| ------- | --------------- | ----------- | --------------- | --------- |
|  Create  |  Content-Type  |  Constant  |  application/xml  |       |
```
![Fluxo](imagens/Screenshot_9.png)

<br>

### ⚙️ Configuração do Content Modifier
Exchange Property
```
| Action  | Name            | Source Type | Source Value    |     Data Type    |
| --------| --------------- | ----------- | --------------- | ---------------- |
|  Create |   _original	    |	Expression	|	    ${body}	  	| java.lang.String | 
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


<br>








