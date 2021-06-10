package com.exasol.cloudetl.kafka

import com.exasol.common.json.JsonMapper

import com.fasterxml.jackson.databind.JsonNode
import org.mockito.ArgumentMatcher
import org.scalactic.TypeCheckedTripleEquals.convertToCheckingEqualizer
import org.scalatest.Assertions.{===, unconstrainedEquality}

/**
 * The order of JSON fields is not deterministic, therefore, we need a custom
 * matchers that compares the JSON object field by field.
 */
class JsonArgumentMatcher(expectedJson: String) extends ArgumentMatcher[String] {

  private[this] val expectedJsonNode = JsonMapper.parseJson[JsonNode](expectedJson)

  final override def matches(argument: String): Boolean =
    JsonMapper.parseJson[JsonNode](argument) === (expectedJsonNode)
}
