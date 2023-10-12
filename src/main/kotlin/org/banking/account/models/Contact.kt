package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
class Contact : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null
    private val firstName: String? = null
    private val lastName: String? = null

    @Column(unique = true)
    private var email: String? = null
    private val iban: String? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    private lateinit var user: User
}