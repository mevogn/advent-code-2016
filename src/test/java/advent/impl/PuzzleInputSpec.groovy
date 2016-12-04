package advent.impl

import advent.PuzzleInput
import spock.lang.Specification


class PuzzleInputSpec extends Specification{
    PuzzleInput puzzleInput = new PuzzleInputImpl()

    def "test input is null for file not found is thrown when invalid path"() {
        given:
        String file = 'xyz'
        String delimiter = ', '

        when:
        String input = puzzleInput.getSingleLineFileAsStrings(file, delimiter)

        then:
        input == null
    }

    def 'test input is not null for valid file'() {
        given:
        String file = 'testing'
        String delimiter = ', '

        when:
        String input = puzzleInput.getSingleLineFileAsStrings(file, delimiter)

        then:
        input
    }

}
