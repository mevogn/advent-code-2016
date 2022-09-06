package advent.impl

import spock.lang.Specification

class BalanceBotsSpec extends Specification {

    BalanceBotsImpl balanceBots = new BalanceBotsImpl(
            null
    )

    def "test getSolution"() {
        given:
        int[] input = [2,1,3,5,4]

        when:
        int returnInt = balanceBots.getSolution(input)

        then:
        returnInt == 3
    }

    def "test getSolution3"() {
        given:
        int[] input = [2,1,4,5,3]

        when:
        int returnInt = balanceBots.getSolution(input)

        then:
        returnInt == 2
    }

    def "test getSolution6"() {
        given:
        int[] input = [6,1,4,6,3,2,7,4]
        int k = 3
        int l = 2

        when:
        int returnInt = balanceBots.getOtherSolution(input, k, l)

        then:
        returnInt == 5
    }

    def "test getSolution6345"() {
        given:
        int[] input = [6,1,4,6,3,2,7,4]
        int k = 3
        int l = 2

        when:
        int returnInt = balanceBots.getOtherSolution2(input, k, l)

        then:
        returnInt == 5
    }
}
