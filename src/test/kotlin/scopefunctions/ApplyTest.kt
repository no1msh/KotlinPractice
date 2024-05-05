package scopefunctions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * - 컨텍스트 객체는 수신자(this)로 사용할 수 있습니다.
 * - 반환 값은 객체 자체입니다.
 *
 * apply는 컨텍스트 객체 자체를 반환하므로 값을 반환하지 않고 주로 수신자 객체의 멤버를 대상으로
 * 작업하는 코드 블록에 사용하는 것이 좋습니다.
 *
 * apply의 가장 일반적인 사용 사례는 객체 구성입니다.
 *
 * 이러한 호출은 "객체에 다음 할당을 적용합니다."라고 읽을 수 있습니다.
 *
 * (apply the following assignments to the object.)
 * */
class ApplyTest {
    @Test
    fun `apply는 this 또는 아무것도 적지 않고 자기 자신을 참조할 수 있다`() {
        // given
        val user = User(name = "bandal", age = 23)
        val newName = "moon"
        val newAge = 25

        // when
        user.apply {
            this.name = newName // this. 을 안써줘도 작동합니다.
            age = newAge // this. 을 써도 똑같이 작동합니다.
        }

        // then
        assertAll(
            { assertThat(user.name).isEqualTo(newName) },
            { assertThat(user.age).isEqualTo(newAge) },
        )
    }

    @Test
    fun `apply의 반환타입은 호출한 자기 자신이다`() {
        // when
        val user = User(name = "bandal", age = 23)
        val actual: User = user.apply {
            name = "moon"
            age = 25
        }

        // then
        assertThat(actual).isSameAs(user)
    }
}
