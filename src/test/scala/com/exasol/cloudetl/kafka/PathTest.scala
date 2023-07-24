package com.exasol.cloudetl.kafka

import com.exasol.ExaExportSpecification
import com.exasol.ExaImportSpecification
import com.exasol.ExaMetadata

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

/**
 * A trait with helper variables for import or export path test classes.
 */
trait PathTest extends AnyFunSuite with BeforeAndAfterEach with MockitoSugar {

  private[kafka] val schema = "myDBSchema"

  private[kafka] var properties = Map(
    "BUCKET_PATH" -> "s3a://my_bucket/folder1/*",
    "DATA_FORMAT" -> "PARQUET",
    "S3_ENDPOINT" -> "s3.eu-central-1.com",
    "S3_ACCESS_KEY" -> "s3_access_key",
    "S3_SECRET_KEY" -> "s3_secret_key"
  )

  private[kafka] var metadata: ExaMetadata = _
  private[kafka] var importSpec: ExaImportSpecification = _
  private[kafka] var exportSpec: ExaExportSpecification = _

  override def beforeEach(): Unit = {
    metadata = mock[ExaMetadata]
    importSpec = mock[ExaImportSpecification]
    exportSpec = mock[ExaExportSpecification]
    ()
  }

}
