package ee.taltech.crossfieldvalidation.hibernate

import ee.taltech.crossfieldvalidation.PersonJson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers


@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class HibernateControllerTest(@Autowired private val mockMvc: MockMvc) {

    private val url = "/api/hibernate"

    @Test
    fun `validateCompanyAForm - success`() {
        mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = PersonJson.validCompanyAForm
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            MockMvcResultHandlers.print()
        }.andExpect {
            status { isOk() }
            content { json(PersonJson.validCompanyAForm, strict = true) }
        }
    }

    @Test
    fun `validateCompanyAForm - failure`() {
        mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = PersonJson.invalidCompanyAForm
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            MockMvcResultHandlers.print()
        }.andExpect {
            status { isBadRequest() }
            content { json("""
                {
                  "errors": [
                    {
                      "field": "birthDate",
                      "message": "Date must be after or equals to 2000-1-1"
                    },
                    {
                      "field": "phoneNumber",
                      "message": "phoneNumber or email must be present"
                    },
                    {
                      "field": "firstName",
                      "message": "size must be between 1 and 128"
                    }
                  ]
                }
            """.trimIndent()) }
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
            content {
                json(
                    """
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
                """.trimIndent()
                )
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
                json(
                    """
                    {
                      "errors": [
                        {
                          "field": "name",
                          "message": "size must be between 2 and 4"
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
