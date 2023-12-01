package ee.taltech.crossfieldvalidation.hibernate

import ee.taltech.crossfieldvalidation.PersonJson
import org.junit.jupiter.api.Nested
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
class KalidationControllerTest(@Autowired private val mockMvc: MockMvc) {

    private val url = "/api/kalidation"

    @Nested
    inner class CompanyA {

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
                      "field": ".birthDate",
                      "message": "Date must be after or equals to 2000-01-01"
                    },
                    {
                      "field": "",
                      "message": "phoneNumber or email must be present"
                    },
                    {
                      "field": ".firstName",
                      "message": "Length must be between 1 and 128"
                    }
                  ]
                }
            """.trimIndent()) }
            }
        }
    }

    @Nested
    inner class CompanyB {

        @Test
        fun `validateCompanyBForm - success`() {
            mockMvc.post(url) {
                contentType = MediaType.APPLICATION_JSON
                content = PersonJson.validCompanyBForm
                accept = MediaType.APPLICATION_JSON
            }.andDo {
                MockMvcResultHandlers.print()
            }.andExpect {
                status { isOk() }
                content { json(PersonJson.validCompanyBForm, strict = true) }
            }
        }

        @Test
        fun `validateCompanyBForm - failure`() {
            mockMvc.post(url) {
                contentType = MediaType.APPLICATION_JSON
                content = PersonJson.invalidCompanyBForm
                accept = MediaType.APPLICATION_JSON
            }.andDo {
                MockMvcResultHandlers.print()
            }.andExpect {
                status { isBadRequest() }
                content { json("""
                    {
                      "errors": [
                        {
                          "field": "socialMedia[2].profileUrl",
                          "message": "Size must be between 1 and 128"
                        },
                        {
                          "field": "socialMedia",
                          "message": "Size must be between 1 and 2"
                        },
                        {
                          "field": "socialMedia[2].platform",
                          "message": "Must be in FACEBOOK, TWITTER_X"
                        },
                        {
                          "field": "height.unit",
                          "message": "Must be in CM"
                        },
                        {
                          "field": "height.value",
                          "message": "numeric value out of bounds (<3 digits>.<0 digits> expected)"
                        }
                      ]
                    }
                """.trimIndent()) }
            }
        }
    }

    @Nested
    inner class General {

        @Test
        fun `validateGeneralForm - success`() {
            mockMvc.post(url) {
                contentType = MediaType.APPLICATION_JSON
                content = PersonJson.validGeneralForm
                accept = MediaType.APPLICATION_JSON
            }.andDo {
                MockMvcResultHandlers.print()
            }.andExpect {
                status { isOk() }
                content { json(PersonJson.validGeneralForm, strict = true) }
            }
        }

        @Test
        fun `validateGeneralForm - failure`() {
            mockMvc.post(url) {
                contentType = MediaType.APPLICATION_JSON
                content = PersonJson.invalidGeneralForm
                accept = MediaType.APPLICATION_JSON
            }.andDo {
                MockMvcResultHandlers.print()
            }.andExpect {
                status { isBadRequest() }
                content { json("""
                    {
                      "errors":[
                        {
                          "field":"constraint",
                          "message":"Invalid request field: gender"
                        }
                      ]
                    }
                """.trimIndent()) }
            }
        }
    }
}
