package ee.taltech.crossfieldvalidation

object PersonJson {

    val validPerson = """
        {
            "type": "PRIVATE",
            "firstName": "Mari",
            "lastName": "Maasikas",
            "phoneNumber": "372123456",
            "emails": ["email@email.ee"],
            "address": {
                "street": "Street",
                "city": "City",
                "state": "State",
                "country": "EE",
                "postalCode": "Ab"
            },
            "height": {
                "value": 180.0,
                "unit": "cm"
            },
            "weight": {
                "value": 80.0,
                "unit": "kg"
            }
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
}
