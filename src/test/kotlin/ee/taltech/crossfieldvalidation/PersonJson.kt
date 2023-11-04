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

    val validCompanyBForm = """
        {
          "agency": "COMPANY_B",
          "firstName": "Mari",
          "lastName": "Maasikas",
          "gender": "MALE",
          "socialMedia": [
            {
              "platform":"FACEBOOK",
              "profileUrl":"https://facebook.com/profile/123"
            },
            {
              "platform":"TWITTER_X",
              "profileUrl":"https://twitter.com/profile/123"
            }
          ],
          "height": {
            "value": 180,
            "unit": "CM"
          }
        }
    """.trimIndent()

    val invalidCompanyBForm = """
        {
          "agency": "COMPANY_B",
          "firstName": "Mari",
          "lastName": "Maasikas",
          "gender": "MALE",
          "socialMedia": [
            {
              "platform":"FACEBOOK",
              "profileUrl":"https://facebook.com/profile/123"
            },
            {
              "platform":"TWITTER_X",
              "profileUrl":"https://twitter.com/profile/123"
            },
            {
              "platform":"TIKTOK",
              "profileUrl":"https://tiktok.com/profile/123-with-extra-long-lengthy-url-with-extra-long-lengthy-url-with-extra-long-lengthy-url-with-extra-long-lengthy-url-123"
            }
          ],
          "height": {
            "value": 1.8,
            "unit": "M"
          }
        }
    """.trimIndent()

    val validGeneralForm = """
        {
            "agency": "GENERAL",
            "firstName": "John",
            "lastName": "Doe",
            "gender": "MALE",
            "birthDate": "1995-12-25",
            "phoneNumber": "37212345679",
            "email": "john@doe.ee",
            "socialMedia": [
                {
                  "platform":"FACEBOOK",
                  "profileUrl":"https://facebook.com/profile/johndoe"
                }
            ],
            "currentLocation": "Tallinn, Estonia",
            "height": {
                "value": 187.12,
                "unit": "CM"
            },
            "weight": {
                "value": 78,
                "unit": "KG"
            },
            "photos": [
                {
                    "typeOfPhoto": "head",
                    "photoUrl": "https://facebook.com/profile/johndoe/pictures/123.png"
                }
            ]
        }
    """.trimIndent()

    val invalidGeneralForm = """
        {
            "agency": "GENERAL",
            "firstName": "John",
            "lastName": "Doe",
            "gender": "MEES",
            "birthDate": "1995-12-25",
            "phoneNumber": "37212345679",
            "email": "john@doe.ee",
            "socialMedia": [
                {
                  "platform":"FACEBOOK",
                  "profileUrl":"https://facebook.com/profile/johndoe"
                }
            ],
            "currentLocation": "Tallinn, Estonia",
            "height": {
                "value": 187.12,
                "unit": "CM"
            },
            "weight": {
                "value": 78,
                "unit": "KG"
            },
            "photos": [
                {
                    "typeOfPhoto": "head",
                    "photoUrl": "https://facebook.com/profile/johndoe/pictures/123.png"
                }
            ]
        }
    """.trimIndent()
}
