package io.chrisdavenport.fuuid

import org.scalacheck._
import java.util.UUID

trait FUUIDArbitraries {
  private implicit val arbUuid: Arbitrary[UUID] = Arbitrary( for {
    l1 <- Gen.choose(Long.MinValue, Long.MaxValue)
    l2 <- Gen.choose(Long.MinValue, Long.MaxValue)
    y <- Gen.oneOf('8', '9', 'a', 'b')
  } yield UUID.fromString(new UUID(l1,l2).toString.updated(14, '4').updated(19, y)))

  implicit val arbFUUID: Arbitrary[FUUID] = Arbitrary(
    Arbitrary.arbitrary[UUID].map(FUUID.fromUUID)
  )

  implicit val arbFUUIDFunction: Arbitrary[FUUID => FUUID] = Arbitrary(
    Arbitrary.arbitrary[FUUID].map(f1 => {f: FUUID => f1})
  )
}

object FUUIDArbitraries extends FUUIDArbitraries