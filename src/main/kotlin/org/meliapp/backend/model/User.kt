package org.meliapp.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long = 0
    var email: String = ""
    var password: String = ""

}