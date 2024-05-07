package collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

/**
 * - 헷갈리는 Collection.min~~ 관련 함수 학습 테스트입니다.
 * - max의 경우 아래 테스트에서 max로 바꿔 생각하면 되기 때문에 생략하였습니다.
 * */

class MinTest {
    data class User(val name: String, val age: Int)

    @Test
    fun `minOf를 사용하여 컬렉션이 가진 값 중 최소값을 찾을 수 있다`() {
        // given
        val users = mutableListOf(User("A", 18), User("B", 20), User("C", 25))

        // when
        val actual: Int = users.minOf { it.age }

        // then
        assertThat(actual).isEqualTo(18)
    }

    @Test
    fun `빈 컬렉션에서 minOf를 사용하면 예외를 던진다`() {
        // given
        val emptyUsers = emptyList<User>()

        // when & then
        assertThrows<NoSuchElementException> { emptyUsers.minOf { it.age } }
    }

    @Test
    fun `minOfOrNull을 사용하여 컬렉션이 가진 값 중 최소 값을 찾을 수 있다`() {
        // given
        val users = mutableListOf(User("A", 18), User("B", 20), User("C", 25))

        // when
        val actual: Int? = users.minOfOrNull { it.age }

        // then
        assertThat(actual).isEqualTo(18)
    }

    @Test
    fun `빈 컬렉션에서 minOfOrNull을 사용하면 null을 반환한다`() {
        // given
        val users = emptyList<User>()

        // when
        val actual: Int? = users.minOfOrNull { it.age }

        // then
        assertNull(actual)
    }

    @Test
    fun `minByOrNull을 사용하여 람다에 명시된 값이 최소인 객체를 찾을 수 있다`() {
        // given
        val users = mutableListOf(User("A", 18), User("B", 20), User("C", 25))

        // when
        val actual: User? = users.minByOrNull { it.age }

        // then
        assertThat(actual).isEqualTo(User("A", 18))
    }

    @Test
    fun `빈 컬렉션에서 minByOrNull을 사용하면 null을 반환한다`() {
        // given
        val emptyUsers = emptyList<User>()

        // when
        val actual: User? = emptyUsers.minByOrNull { it.age }

        // then
        assertNull(actual)
    }

    @Test
    fun `같은 최소값을 가진 객체 여러개가 있다면 minByOrNull을 사용할 때 먼저 들어온 값이 반환된다`() {
        // given
        val users = mutableListOf(User("A", 20), User("B", 20), User("C", 25))

        // when
        val actual = users.minByOrNull { it.age }

        // then
        assertThat(actual).isEqualTo(User("A", 20))
    }

    @Test
    fun `minOfWith을 통해 Comparator를 사용하여 최소 값의 기준을 정의 할 수 있다`() {
        // given
        val users = mutableListOf(User("A", 20), User("B", 20), User("C", 25))

        // when
        val actual = users.minOfWith(
            { o1, o2 ->
                o1.age - o2.age // 음수가 나오면 o2가 더 큰값
            }
        ) { it }

        // then
        assertThat(actual).isEqualTo(User("A", 20))
    }

    @Test
    fun `빈 컬렉션에서 minOfWith을 사용하면 예외를 던진다`() {
        // given
        val emptyUsers = emptyList<User>()

        // when & then
        assertThrows<NoSuchElementException> {
            emptyUsers.minOfWith(comparator = { o1: User, o2: User -> o1.age - o2.age }) { it }
        }
    }

    @Test
    fun `빈 컬렉션에서 minOfWithOrNull을 사용하면 null을 반환한다`() {
        // given
        val emptyUsers = emptyList<User>()

        // when
        val actual = emptyUsers.minOfWithOrNull({ o1, o2 -> o1.age - o2.age }) { it }

        // then
        assertNull(actual)
    }
}
