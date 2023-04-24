package ee.taltech.crossfieldvalidation.hibernate

import com.fasterxml.jackson.databind.ObjectMapper
import ee.taltech.crossfieldvalidation.hibernate.model.PrivatePerson
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Address
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Height
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Units
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Weight
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class HibernateControllerTest(
    @Autowired
    private val mockMvc: MockMvc,
    @Autowired
    private val objectMapper: ObjectMapper
) {

    private lateinit var validPerson: PrivatePerson
    private lateinit var invalidPerson: PrivatePerson

    @BeforeEach
    fun setup() {
        validPerson = PrivatePerson(
            firstName = "Mari",
            lastName = "Maasikas",
            phoneNumber = "372123456",
            emails = listOf("email@email.com"),
            address = Address(
                street = "Street",
                city = "City",
                state = "State",
                country = "Country",
                postalCode = "Postal code"
            ),
            weight = Weight(
                value = BigDecimal("80.0"),
                unit = Units.KG
            ),
            height = Height(
                value = BigDecimal("180.35"),
                unit = Units.CM
            )
        )
        invalidPerson = PrivatePerson(
            firstName = "Toomas",
            lastName = "Maasi",
            phoneNumber = null,
            emails = null,
            address = Address(
                street = "S",
                city = "",
                state = "State",
                country = "Country",
                postalCode = "Postal code"
            ),
            weight = Weight(
                value = BigDecimal("80.5555"),
                unit = Units.KG
            ),
            height = Height(
                value = BigDecimal("234.3512"),
                unit = Units.CM
            )
        )
    }

    @Test
    fun `validatePerson - success`() {
        val json = objectMapper.writeValueAsString(validPerson)

        mockMvc.post("/api/hibernate") {
            contentType = MediaType.APPLICATION_JSON
            content = json
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(validPerson), strict = true) }
        }
    }

    @Test
    fun `validatePerson - failure`() {
        val json = objectMapper.writeValueAsString(invalidPerson)

        mockMvc.post("/api/hibernate") {
            contentType = MediaType.APPLICATION_JSON
            content = json
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
            content { json("""
                {
                  "errors": [
                    {
                      "field": "address.city",
                      "message": "size must be between 2 and 2147483647"
                    },
                    {
                      "field": "firstName",
                      "message": "size must be between 2 and 4"
                    },
                    {
                      "field": "atLeastPhoneOrEmailPresent",
                      "message": "Phone or Email must be present"
                    },
                    {
                      "field": "height.value",
                      "message": "numeric value out of bounds (<3 digits>.<2 digits> expected)"
                    },
                    {
                      "field": "weight.value",
                      "message": "numeric value out of bounds (<3 digits>.<2 digits> expected)"
                    },
                    {
                      "field": "address.street",
                      "message": "size must be between 2 and 2147483647"
                    }
                  ]
                }
            """.trimIndent())
            }
        }
    }
}
