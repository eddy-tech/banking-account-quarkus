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
@Table(name = "addresses")
class Address : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var addressId: Long? = null
    private val street: String? = null
    private val houseNumber: Int? = null
    private val zipCode: Int? = null
    private val city: String? = null
    private val country: String? = null

    @OneToOne
    @JoinColumn(name = "user_id")
    private lateinit var user: User
}