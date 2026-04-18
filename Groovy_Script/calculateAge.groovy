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
