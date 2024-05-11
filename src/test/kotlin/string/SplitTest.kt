package string

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SplitTest {
    @Test
    fun `split으로 문자열을 String List 형태로 바꿀 수 있다`() {
        // given
        val input = "a b c"

        // when
        val actual = input.split(" ")

        // then
        assertThat(actual).isEqualTo(listOf("a", "b", "c"))
    }

    @Test
    fun `delimiters 에 여러개의 기준 문자열을 넣을 수 있다`() {
        // given
        val input = "a b c-d e-f"

        // when
        val actual = input.split(" ", "-")

        // then
        assertThat(actual).isEqualTo(listOf("a", "b", "c", "d", "e", "f"))
    }

    @Test
    fun `"" 을 delimiter로 사용하면 앞뒤로 ""도 포함이 된다`() {
        // given
        val input = "abc"

        // when
        val actual = input.split("")

        // then
        assertThat(actual).isEqualTo(listOf("", "a", "b", "c", ""))
    }

    @Test
    fun `붙어있는 각각의 문자를 나누고 싶다면 map을 사용한다`() {
        // given
        val input = "abc"

        // when
        val actual = input.map(Char::toString)

        // then
        assertThat(actual).isEqualTo(listOf("a", "b", "c"))
    }

    @Test
    fun `문자를 기준으로 delimiter를 사용하면 ignoreCase를 통해 대소문자 구분을 지정해줄 수 있다`() {
        // given
        val input = "1a2A3A4a5"

        // when
        val actual = input.split("a", ignoreCase = true)

        // then
        assertThat(actual).isEqualTo(listOf("1", "2", "3", "4", "5"))
    }

    @Test
    fun `정규식으로 문자열을 구분할 수 있다`() {
        // given
        val input = "1a2B3d4Z5"

        // when
        val actual = input.split(Regex("[a-zA-Z]"))

        // then
        assertThat(actual).isEqualTo(listOf("1", "2", "3", "4", "5"))
    }

    @Test
    fun `만약 문자열의 양끝을 제거하고 split을 사용하고 싶다면`() {
        // given
        val input = "[1,2,3,4,5]"

        // when
        val actual = input.removeSurrounding(prefix = "[", suffix = "]").split(",")

        // then
        assertThat(actual).isEqualTo(listOf("1", "2", "3", "4", "5"))
    }
}
