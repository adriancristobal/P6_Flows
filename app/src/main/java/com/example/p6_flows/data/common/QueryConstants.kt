package com.example.p5_aplicationfutbolfinal.data.common

object QueryConstants {

    const val GET_ALL_TEAMS = "SELECT * FROM TeamEntity"
    const val INSERT_TEAM_WITHOUT_ID = "INSERT INTO TeamEntity (name, numGoals, numMatches, numWins, numDraws, numLoses, numGoalsAgainst, ranking) VALUES (:name, :numGoals, :numMatches, :numWins, :numDraws, :numLoses, :numGoalsAgainst, :ranking)"
}