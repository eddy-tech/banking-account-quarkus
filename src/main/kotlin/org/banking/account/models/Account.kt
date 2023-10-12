package org.banking.account.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.experimental.SuperBuilder

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
@SuperBuilder
@Table(name = "accounts")
class Account : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var accountId: Long? = null
    private val iban: String? = null
}
