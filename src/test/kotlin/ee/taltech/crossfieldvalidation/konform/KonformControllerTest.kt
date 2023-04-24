package ee.taltech.crossfieldvalidation.konform

import ee.taltech.crossfieldvalidation.PersonJson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class KonformControllerTest(@Autowired private val mockMvc: MockMvc) {

    private val url = "/api/konform"

    @Test
    fun `validatePerson - success`() {
        mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = PersonJson.validPerson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json(PersonJson.validPerson, strict = true) }
        }
    }

    @Test
    fun `validatePerson - failure`() {
        mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = PersonJson.invalidPerson
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
            content { json("""
                {
                  "errors": [
                    {
                      "field": "address.city",
                      "message": "Size must be greater than or equal to 2"
                    },
                    {
                      "field": "firstName",
                      "message": "Size must be between 2 and 4"
                    },
                    {
                      "field": "phoneNumber",
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
                      "message": "Size must be greater than or equal to 2"
                    }
                  ]
                }
            """.trimIndent())
            }
        }
    }
}
