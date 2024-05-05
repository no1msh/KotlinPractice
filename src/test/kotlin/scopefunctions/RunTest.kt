package scopefunctions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

/**
 * - 컨텍스트 객체는 수신자(this)로 사용할 수 있습니다.
 * - 반환 값은 람다 결과입니다.
 *
 * run은 with과 동일한 기능을 수행하지만 확장함수로 구현됩니다.
 *
 * 따라서 let과 마찬가지로 점 표기법을 사용하여 컨텍스트 객체에서 호출할 수 있습니다.
 *
 * run은 람다가 객체를 초기화하고 반환값을 계산할 때 유용합니다.
 *
 * 코드에서 확장함수가 아닌 run은 "코드 블록을 실행하고 결과를 계산합니다."로 읽을 수 있습니다.
 *
 * (run the code block and compute the result.)
 * */

class RunTest {
    @Test
    fun `run은 this 또는 아무것도 적지 않고 자기 자신을 참조 할 수 있다`() {
        // given
        val user = User(name = "bandal", age = 23)
        val newName = "moon"
        val newAge = 25

        // when
        user.run {
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
    fun `run은 반환값이 람다의 결과값이다`() {
        // given
        val user = User(name = "bandal", age = 23)
        val newName = "moon"
        val newAge = 25

        // when
        val userName: String = user.run {
            name = newName
            age = newAge
            name
        }

        // then
        assertThat(userName).isEqualTo(newName)
    }

    @Test
    fun `run을 비확장함수로 사용하여도 람다의 결과 값이 반환된다`() {
        // given
        val name = "bandal"
        val age = 23

        // when
        val actual: User = run {
            User(name, age)
        }

        // then
        assertThat(actual).isEqualTo(User(name, age))
    }
}
