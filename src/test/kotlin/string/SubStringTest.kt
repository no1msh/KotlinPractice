package string

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * 알고리즘 할 때 매일 헷갈리는 subString() 함수 정리
 * 결론! range를 사용하는 형태가 훨씬 직관적이다.
 * */

class SubStringTest {
    @Test
    fun `subString(range)는 첫 인덱스와 끝인덱스를 모두 포함한다`() {
        // given
        val given = "대한민국"

        // when
        val actual: String = given.substring(0..1)

        // then
        assertThat(actual).isEqualTo("대한")
    }

    @Test
    fun `subString(range)는 until을 사용하여 마지막 인덱스는 포함되지 않게 할 수 있다`() {
        // given
        val given = "대한민국"

        // when
        val actual: String = given.substring(0 until 1)

        // then
        assertThat(actual).isEqualTo("대")
    }

    @Test
    fun `subString(startIndex, endIndex)는 첫인덱스를 포함하고 마지막 인덱스를 포함하지 않는다`() {
        // given
        val given = "대한민국"

        // when
        val actual: String = given.substring(0, 1)

        // then
        assertThat(actual).isEqualTo("대")
    }

    @Test
    fun `subString(startIndex)는 첫인덱스를 포함하고 마지막 인덱스까지 포함한다`() {
        // given
        val given = "대한민국"

        // when
        val actual: String = given.substring(2)

        // then
        assertThat(actual).isEqualTo("민국")
    }
}
