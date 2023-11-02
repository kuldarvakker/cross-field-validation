package ee.taltech.crossfieldvalidation

object PersonJson {

    val validCompanyAForm = """
        {
          "agency": "COMPANY_A",
          "firstName": "Mari",
          "lastName": "Maasikas",
          "birthDate": "2019-08-24",
          "phoneNumber": "+372123456789",
          "email": "email@email.ee"
        }
    """.trimIndent()

    val invalidCompanyAForm = """
        {
          "agency": "COMPANY_A",
          "firstName": "",
          "lastName": "Maasikas",
          "birthDate": "1999-01-01"
        }
    """.trimIndent()

    val invalidPerson = """
        {
            "type": "PRIVATE",
            "firstName": "Toomas",
            "lastName": "Maasi",
            
            
            "address": {
                "street": "S",
                "city": "",
                "state": "State",
                "country": "EE",
                "postalCode": "Ab"
            },
            "height": {
                "value": 180.024,
                "unit": "cm"
            },
            "weight": {
                "value": 3180,
                "unit": "kg"
            }
        }
    """.trimIndent()

    val validCompany = """
        {
            "type": "COMPANY",
            "name": "Name"
        }
    """.trimIndent()

    val invalidCompany = """
        {
            "type": "COMPANY",
            "name": "Too Lengthy Name"
        }
    """.trimIndent()
}
