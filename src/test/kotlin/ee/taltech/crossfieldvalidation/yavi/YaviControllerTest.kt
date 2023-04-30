package ee.taltech.crossfieldvalidation.yavi

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
class YaviControllerTest(@Autowired private val mockMvc: MockMvc) {

    private val url = "/api/yavi"

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
                      "message": "The size of \"address.city\" must be greater than or equal to 2. The given size is 0"
                    },
                    {
                      "field": "firstName",
                      "message": "The size of \"firstName\" must be less than or equal to 4. The given size is 6"
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
                      "message": "The size of \"address.street\" must be greater than or equal to 2. The given size is 1"
                    }
                  ]
                }
            """.trimIndent())
            }
        }
    }

    @Test
    fun `validateCompany - success`() {
        mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = PersonJson.validCompany
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json(PersonJson.validCompany, strict = true) }
        }
    }

    @Test
    fun `validateCompany - failure`() {
        mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = PersonJson.invalidCompany
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
            content {
                json("""
                    {
                      "errors": [
                        {
                          "field": "name",
                          "message": "The size of \"name\" must be less than or equal to 4. The given size is 16"
                        }
                      ]
                    }
                """.trimIndent(),
                    strict = true
                )
            }
        }
    }
}
