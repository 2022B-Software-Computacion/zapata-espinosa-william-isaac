package com.example.deberzapata

class DistributorProvider {
    companion object {
        val distributorList = listOf<Distributor>(
            Distributor(
                "Agipgas - Sangolqui",
                "Gas",
                "45 - 60 min",
                4.0f,
                "https://cdnimg.bnamericas.com/LJPrfYuOvrGJnMPfptaxcnnUzitmsDTLBfnEGGrKOMvRHDmeGQAJNnYXSeeCzudi.png",
                "Envío gratis"
            ),
            Distributor(
                "Agas Cía. Ltda",
                "Gas",
                "60 - 75 min",
                3.3f,
                "https://agas.ec/img/agas-logo-1591040782.jpg",
                "Envío 1.69$"
            ),
            Distributor(
                "duragas GLP DOMÉSTICO",
                "Gas",
                "55 - 65 min",
                4.5f,
                "https://i.pinimg.com/originals/36/8e/60/368e607e53d36b3e4e99e1c9785e4ebb.png",
                "Envío variable"
            )
        )
    }
}