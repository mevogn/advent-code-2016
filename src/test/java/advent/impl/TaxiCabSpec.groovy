package advent.impl

import advent.PuzzleInput
import advent.TaxiCab
import spock.lang.Specification

class TaxiCabSpec extends Specification {

    private PuzzleInput puzzleInput = Mock()
    TaxiCab taxiCab = new TaxiCabImpl(puzzleInput)

    def "test simple path"() {
        given:
        String[] directions = ['R1', 'R3']

        when:
        int distance = taxiCab.getShortestPath(true, directions)

        then:
        distance == 4
    }
}