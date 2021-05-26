package com.exasol.cloudetl.kafka.deserialization

sealed trait GlobalFieldSpecification

/**
 * A sealed trait for all possible ways to refernce a kafka record's key, value
 */
sealed trait FieldSpecification extends GlobalFieldSpecification

/**
 * Marker trait for all specs that reference the key
 */
sealed trait KeySpecification extends FieldSpecification

/**
 * Marker trait for all specs that reference the value
 */
sealed trait ValueSpecification extends FieldSpecification

/**
 * Marker trait for all specs that mean the full record
 */
sealed trait FullRecord extends FieldSpecification

/**
 * The specification <pre>key</pre>
 */
object RecordKey extends KeySpecification with FullRecord

/**
 * The specification <pre>value</pre>
 */
object RecordValue extends ValueSpecification with FullRecord

/**
 * Marker trait for a reference to alle fields of the key or value
 */
trait AllFieldsSpecification

/**
 * The specification <pre>key.*</pre>
 */
object RecordKeyFields extends KeySpecification with AllFieldsSpecification

/**
 * The specification <pre>value.*</pre>
 */
object RecordValueFields extends ValueSpecification with AllFieldsSpecification

/**
 * A reference to a field of the record
 */
sealed class ConcreteField(val fieldName: String) extends FieldSpecification

/**
 * The specification <pre>key.<i>fieldname</i>/pre>
 */
final case class RecordKeyField(override val fieldName: String)
    extends ConcreteField(fieldName)
    with KeySpecification

/**
 * The specification <pre>value.<i>fieldname</i>/pre>
 */
final case class RecordValueField(override val fieldName: String)
    extends ConcreteField(fieldName)
    with ValueSpecification

/**
 * The specification <pre>timestamp</pre>
 */
case object TimestampField extends GlobalFieldSpecification
