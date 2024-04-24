package collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Collection을 자르는 두 방법: subList와 slice 정리
 * 결론! slice(range)를 활용하는게 더 직관적인 범위 표현이 가능하다.
 * */
class SubListAndSliceTest {
    @Test
    fun `subList(fromIndex, toIndex)는 첫 인덱스를 포함하고 마지막 인덱스를 포함하지 않는다`() {
        // given
        val given: List<Int> = listOf(0, 1, 2, 3, 4)

        // when
        val actual: List<Int> = given.subList(fromIndex = 0, toIndex = 2)

        // then
        assertThat(actual).isEqualTo(listOf(0, 1))
    }

    @Test
    fun `slice(indices)는 range를 통해 첫 인덱스와 마지막 인덱스를 포함한다`() {
        // given
        val given: List<Int> = listOf(0, 1, 2, 3, 4)

        // when
        val actual: List<Int> = given.slice(indices = 0..1)

        // then
        assertThat(actual).isEqualTo(listOf(0, 1))
    }

    @Test
    fun `slice(indices)는 until을 통해 첫 인덱스는 포함하고 마지막 인덱스는 포함하지 않는다`() {
        // given
        val given: List<Int> = listOf(0, 1, 2, 3, 4)

        // when
        val actual: List<Int> = given.slice(indices = 0 until 2)

        // then
        assertThat(actual).isEqualTo(listOf(0, 1))
    }
}
