#include <enunciate-common.c>
#ifndef DEF_hegira_api_ns0_status_H
#define DEF_hegira_api_ns0_status_H

/**
 * (no documentation provided)
 */
struct hegira_api_ns0_status {


  /**
   * OK, WARNING or ERROR.
   */
  xmlChar *State;

  /**
   * 
   */
  xmlChar *message;

  /**
   * 
   */
  xmlChar *error_code;
};

/**
 * Reads a Status element from XML. The element to be read is "status", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The Status, or NULL in case of error.
 */
struct hegira_api_ns0_status *xml_read_hegira_api_ns0_status(xmlTextReaderPtr reader);

/**
 * Writes a Status to XML under element name "status".
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
int xml_write_hegira_api_ns0_status(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status);

/**
 * Frees a Status.
 *
 * @param _status The Status to free.
 */
void free_hegira_api_ns0_status(struct hegira_api_ns0_status *_status);

/**
 * Reads a Status element from XML. The element to be read is "status", and
 * it is assumed that the reader is already pointing to the element.
 *
 * @param reader The XML reader.
 * @return The Status, or NULL in case of error.
 */
struct hegira_api_ns0_status *xmlTextReaderReadNs0StatusElement(xmlTextReaderPtr reader);

/**
 * Writes a Status to XML under element name "status".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0StatusElement(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status);

/**
 * Writes a Status to XML under element name "status".
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0StatusElementNS(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status, int writeNamespaces);

/**
 * Frees the children of a Status.
 *
 * @param _status The Status whose children are to be free.
 */
static void freeNs0StatusElement(struct hegira_api_ns0_status *_status);

/**
 * Reads a Status from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Status, or NULL in case of error.
 */
static struct hegira_api_ns0_status *xmlTextReaderReadNs0StatusType(xmlTextReaderPtr reader);

/**
 * Writes a Status to XML.
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0StatusType(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status);

/**
 * Frees the elements of a Status.
 *
 * @param _status The Status to free.
 */
static void freeNs0StatusType(struct hegira_api_ns0_status *_status);

#endif /* DEF_hegira_api_ns0_status_H */
#ifndef DEF_hegira_api_ns0_ZKobject_H
#define DEF_hegira_api_ns0_ZKobject_H

/**
 *  @author Marco Scavuzzo


 */
struct hegira_api_ns0_ZKobject {


  /**
   * The lower extreme of the generated range of unique ids.
   */
  xmlChar *lowExtreme;

  /**
   * The highest extreme of the generated range of unique ids.
   */
  xmlChar *highExtreme;

  /**
   * The generated unique id.
   */
  xmlChar *id;

  /**
   * The status given in response to the REST request.
   */
  struct hegira_api_ns0_status *requestStatus;
};

/**
 * Reads a ZKobject element from XML. The element to be read is "ZKobject", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The ZKobject, or NULL in case of error.
 */
struct hegira_api_ns0_ZKobject *xml_read_hegira_api_ns0_ZKobject(xmlTextReaderPtr reader);

/**
 * Writes a ZKobject to XML under element name "ZKobject".
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
int xml_write_hegira_api_ns0_ZKobject(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject);

/**
 * Frees a ZKobject.
 *
 * @param _zKobject The ZKobject to free.
 */
void free_hegira_api_ns0_ZKobject(struct hegira_api_ns0_ZKobject *_zKobject);

/**
 * Reads a ZKobject element from XML. The element to be read is "ZKobject", and
 * it is assumed that the reader is already pointing to the element.
 *
 * @param reader The XML reader.
 * @return The ZKobject, or NULL in case of error.
 */
struct hegira_api_ns0_ZKobject *xmlTextReaderReadNs0ZKobjectElement(xmlTextReaderPtr reader);

/**
 * Writes a ZKobject to XML under element name "ZKobject".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0ZKobjectElement(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject);

/**
 * Writes a ZKobject to XML under element name "ZKobject".
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0ZKobjectElementNS(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject, int writeNamespaces);

/**
 * Frees the children of a ZKobject.
 *
 * @param _zKobject The ZKobject whose children are to be free.
 */
static void freeNs0ZKobjectElement(struct hegira_api_ns0_ZKobject *_zKobject);

/**
 * Reads a ZKobject from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The ZKobject, or NULL in case of error.
 */
static struct hegira_api_ns0_ZKobject *xmlTextReaderReadNs0ZKobjectType(xmlTextReaderPtr reader);

/**
 * Writes a ZKobject to XML.
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0ZKobjectType(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject);

/**
 * Frees the elements of a ZKobject.
 *
 * @param _zKobject The ZKobject to free.
 */
static void freeNs0ZKobjectType(struct hegira_api_ns0_ZKobject *_zKobject);

#endif /* DEF_hegira_api_ns0_ZKobject_H */
#ifndef DEF_hegira_api_ns0_status_M
#define DEF_hegira_api_ns0_status_M

/**
 * Reads a Status element from XML. The element to be read is "status", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The Status, or NULL in case of error.
 */
struct hegira_api_ns0_status *xml_read_hegira_api_ns0_status(xmlTextReaderPtr reader) {
  int status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
  return xmlTextReaderReadNs0StatusElement(reader);
}

/**
 * Writes a Status to XML under element name "status".
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @return 1 if successful, 0 otherwise.
 */
int xml_write_hegira_api_ns0_status(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status) {
  return xmlTextWriterWriteNs0StatusElementNS(writer, _status, 1);
}

/**
 * Frees a Status.
 *
 * @param _status The Status to free.
 */
void free_hegira_api_ns0_status(struct hegira_api_ns0_status *_status) {
  freeNs0StatusType(_status);
  free(_status);
}

/**
 * Reads a Status element from XML. The element to be read is "status", and
 * it is assumed that the reader is pointing to that element.
 *
 * @param reader The XML reader.
 * @return The Status, or NULL in case of error.
 */
struct hegira_api_ns0_status *xmlTextReaderReadNs0StatusElement(xmlTextReaderPtr reader) {
  struct hegira_api_ns0_status *_status = NULL;

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "status", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Attempting to read root element {}status.\n");
#endif
    _status = xmlTextReaderReadNs0StatusType(reader);
  }
#if DEBUG_ENUNCIATE
  if (_status == NULL) {
    if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
      printf("attempt to read {}status failed. current element: {}%s\n",  xmlTextReaderConstLocalName(reader));
    }
    else {
      printf("attempt to read {}status failed. current element: {%s}%s\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
    }
  }
#endif

  return _status;
}

/**
 * Writes a Status to XML under element name "status".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteNs0StatusElement(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status) {
  return xmlTextWriterWriteNs0StatusElementNS(writer, _status, 0);
}

/**
 * Writes a Status to XML under element name "status".
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteNs0StatusElementNS(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status, int writeNamespaces) {
  int totalBytes = 0;
  int status;

  status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "status", NULL);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write start element {}status. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

#if DEBUG_ENUNCIATE > 1
  printf("writing type {}status for root element {}status...\n");
#endif
  status = xmlTextWriterWriteNs0StatusType(writer, _status);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write type for start element {}status. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  status = xmlTextWriterEndElement(writer);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to end element {}status. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  return totalBytes;
}

/**
 * Frees the children of a Status.
 *
 * @param _status The Status whose children are to be free.
 */
static void freeNs0StatusElement(struct hegira_api_ns0_status *_status) {
  freeNs0StatusType(_status);
}

/**
 * Reads a Status from XML. The reader is assumed to be at the start element.
 *
 * @return the Status, or NULL in case of error.
 */
static struct hegira_api_ns0_status *xmlTextReaderReadNs0StatusType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct hegira_api_ns0_status *_status = calloc(1, sizeof(struct hegira_api_ns0_status));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0StatusType(_status);
        free(_status);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "status", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}status of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}status of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StatusType(_status);
          free(_status);
          return NULL;
        }

        _status->State = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "message", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}message of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}message of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StatusType(_status);
          free(_status);
          return NULL;
        }

        _status->message = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "error_code", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}error_code of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}error_code of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0StatusType(_status);
          free(_status);
          return NULL;
        }

        _status->error_code = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}status.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}status. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _status;
}

/**
 * Writes a Status to XML.
 *
 * @param writer The XML writer.
 * @param _status The Status to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0StatusType(xmlTextWriterPtr writer, struct hegira_api_ns0_status *_status) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_status->State != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "status", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}status. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}status...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_status->State));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}status. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}status. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_status->message != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "message", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}message. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}message...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_status->message));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}message. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}message. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_status->error_code != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "error_code", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}error_code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}error_code...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_status->error_code));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}error_code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}error_code. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a Status.
 *
 * @param _status The Status to free.
 */
static void freeNs0StatusType(struct hegira_api_ns0_status *_status) {
  int i;
  if (_status->State != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor State of type hegira_api_ns0_status...\n");
#endif
    freeXsStringType(_status->State);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor State of type hegira_api_ns0_status...\n");
#endif
    free(_status->State);
  }
  if (_status->message != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor message of type hegira_api_ns0_status...\n");
#endif
    freeXsStringType(_status->message);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor message of type hegira_api_ns0_status...\n");
#endif
    free(_status->message);
  }
  if (_status->error_code != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor error_code of type hegira_api_ns0_status...\n");
#endif
    freeXsStringType(_status->error_code);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor error_code of type hegira_api_ns0_status...\n");
#endif
    free(_status->error_code);
  }
}
#endif /* DEF_hegira_api_ns0_status_M */
#ifndef DEF_hegira_api_ns0_ZKobject_M
#define DEF_hegira_api_ns0_ZKobject_M

/**
 * Reads a ZKobject element from XML. The element to be read is "ZKobject", and
 * it is assumed that the reader is pointing to the XML document (not the element).
 *
 * @param reader The XML reader.
 * @return The ZKobject, or NULL in case of error.
 */
struct hegira_api_ns0_ZKobject *xml_read_hegira_api_ns0_ZKobject(xmlTextReaderPtr reader) {
  int status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
  return xmlTextReaderReadNs0ZKobjectElement(reader);
}

/**
 * Writes a ZKobject to XML under element name "ZKobject".
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @return 1 if successful, 0 otherwise.
 */
int xml_write_hegira_api_ns0_ZKobject(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject) {
  return xmlTextWriterWriteNs0ZKobjectElementNS(writer, _zKobject, 1);
}

/**
 * Frees a ZKobject.
 *
 * @param _zKobject The ZKobject to free.
 */
void free_hegira_api_ns0_ZKobject(struct hegira_api_ns0_ZKobject *_zKobject) {
  freeNs0ZKobjectType(_zKobject);
  free(_zKobject);
}

/**
 * Reads a ZKobject element from XML. The element to be read is "ZKobject", and
 * it is assumed that the reader is pointing to that element.
 *
 * @param reader The XML reader.
 * @return The ZKobject, or NULL in case of error.
 */
struct hegira_api_ns0_ZKobject *xmlTextReaderReadNs0ZKobjectElement(xmlTextReaderPtr reader) {
  struct hegira_api_ns0_ZKobject *_zKobject = NULL;

  if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
    && xmlStrcmp(BAD_CAST "ZKobject", xmlTextReaderConstLocalName(reader)) == 0
    && xmlTextReaderConstNamespaceUri(reader) == NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Attempting to read root element {}ZKobject.\n");
#endif
    _zKobject = xmlTextReaderReadNs0ZKobjectType(reader);
  }
#if DEBUG_ENUNCIATE
  if (_zKobject == NULL) {
    if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
      printf("attempt to read {}ZKobject failed. current element: {}%s\n",  xmlTextReaderConstLocalName(reader));
    }
    else {
      printf("attempt to read {}ZKobject failed. current element: {%s}%s\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
    }
  }
#endif

  return _zKobject;
}

/**
 * Writes a ZKobject to XML under element name "ZKobject".
 * Does NOT write the namespace prefixes.
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteNs0ZKobjectElement(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject) {
  return xmlTextWriterWriteNs0ZKobjectElementNS(writer, _zKobject, 0);
}

/**
 * Writes a ZKobject to XML under element name "ZKobject".
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @param writeNamespaces Whether to write the namespace prefixes.
 * @return 1 if successful, 0 otherwise.
 */
static int xmlTextWriterWriteNs0ZKobjectElementNS(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject, int writeNamespaces) {
  int totalBytes = 0;
  int status;

  status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "ZKobject", NULL);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write start element {}ZKobject. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

#if DEBUG_ENUNCIATE > 1
  printf("writing type {}ZKobject for root element {}ZKobject...\n");
#endif
  status = xmlTextWriterWriteNs0ZKobjectType(writer, _zKobject);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to write type for start element {}ZKobject. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  status = xmlTextWriterEndElement(writer);
  if (status < 0) {
#if DEBUG_ENUNCIATE
    printf("unable to end element {}ZKobject. status: %i\n", status);
#endif
    return status;
  }
  totalBytes += status;

  return totalBytes;
}

/**
 * Frees the children of a ZKobject.
 *
 * @param _zKobject The ZKobject whose children are to be free.
 */
static void freeNs0ZKobjectElement(struct hegira_api_ns0_ZKobject *_zKobject) {
  freeNs0ZKobjectType(_zKobject);
}

/**
 * Reads a ZKobject from XML. The reader is assumed to be at the start element.
 *
 * @return the ZKobject, or NULL in case of error.
 */
static struct hegira_api_ns0_ZKobject *xmlTextReaderReadNs0ZKobjectType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct hegira_api_ns0_ZKobject *_zKobject = calloc(1, sizeof(struct hegira_api_ns0_ZKobject));



  if (xmlTextReaderIsEmptyElement(reader) == 0) {
    depth = xmlTextReaderDepth(reader);//track the depth.
    status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);

    while (xmlTextReaderDepth(reader) > depth) {
      if (status < 1) {
        //panic: XML read error.
#if DEBUG_ENUNCIATE
        printf("Failure to advance to next child element.\n");
#endif
        freeNs0ZKobjectType(_zKobject);
        free(_zKobject);
        return NULL;
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "lowExtreme", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}lowExtreme of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}lowExtreme of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0ZKobjectType(_zKobject);
          free(_zKobject);
          return NULL;
        }

        _zKobject->lowExtreme = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "highExtreme", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}highExtreme of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}highExtreme of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0ZKobjectType(_zKobject);
          free(_zKobject);
          return NULL;
        }

        _zKobject->highExtreme = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "id", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}id of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
        _child_accessor = xmlTextReaderReadXsStringType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}id of type {http://www.w3.org/2001/XMLSchema}string.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0ZKobjectType(_zKobject);
          free(_zKobject);
          return NULL;
        }

        _zKobject->id = ((xmlChar*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else if (xmlTextReaderNodeType(reader) == XML_READER_TYPE_ELEMENT
        && xmlStrcmp(BAD_CAST "requestStatus", xmlTextReaderConstLocalName(reader)) == 0
        && xmlTextReaderConstNamespaceUri(reader) == NULL) {

#if DEBUG_ENUNCIATE > 1
        printf("Attempting to read choice {}requestStatus of type {}status.\n");
#endif
        _child_accessor = xmlTextReaderReadNs0StatusType(reader);
        if (_child_accessor == NULL) {
#if DEBUG_ENUNCIATE
          printf("Failed to read choice {}requestStatus of type {}status.\n");
#endif
          //panic: unable to read the child element for some reason.
          freeNs0ZKobjectType(_zKobject);
          free(_zKobject);
          return NULL;
        }

        _zKobject->requestStatus = ((struct hegira_api_ns0_status*)_child_accessor);
        status = xmlTextReaderAdvanceToNextStartOrEndElement(reader);
      }
      else {
#if DEBUG_ENUNCIATE > 1
        if (xmlTextReaderConstNamespaceUri(reader) == NULL) {
          printf("unknown child element {}%s for type {}ZKobject.  Skipping...\n",  xmlTextReaderConstLocalName(reader));
        }
        else {
          printf("unknown child element {%s}%s for type {}ZKobject. Skipping...\n", xmlTextReaderConstNamespaceUri(reader), xmlTextReaderConstLocalName(reader));
        }
#endif
        status = xmlTextReaderSkipElement(reader);
      }
    }
  }

  return _zKobject;
}

/**
 * Writes a ZKobject to XML.
 *
 * @param writer The XML writer.
 * @param _zKobject The ZKobject to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0ZKobjectType(xmlTextWriterPtr writer, struct hegira_api_ns0_ZKobject *_zKobject) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;
  if (_zKobject->lowExtreme != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "lowExtreme", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}lowExtreme. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}lowExtreme...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_zKobject->lowExtreme));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}lowExtreme. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}lowExtreme. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_zKobject->highExtreme != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "highExtreme", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}highExtreme. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}highExtreme...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_zKobject->highExtreme));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}highExtreme. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}highExtreme. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_zKobject->id != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "id", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {http://www.w3.org/2001/XMLSchema}string for element {}id...\n");
#endif
    status = xmlTextWriterWriteXsStringType(writer, (_zKobject->id));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {http://www.w3.org/2001/XMLSchema}string for element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}id. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }
  if (_zKobject->requestStatus != NULL) {
    status = xmlTextWriterStartElementNS(writer, NULL, BAD_CAST "requestStatus", NULL);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write start element {}requestStatus. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
#if DEBUG_ENUNCIATE > 1
    printf("writing type {}status for element {}requestStatus...\n");
#endif
    status = xmlTextWriterWriteNs0StatusType(writer, (_zKobject->requestStatus));
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write type {}status for element {}requestStatus. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;

    status = xmlTextWriterEndElement(writer);
    if (status < 0) {
#if DEBUG_ENUNCIATE
      printf("Failed to write end element {}requestStatus. status: %i\n", status);
#endif
      return status;
    }
    totalBytes += status;
  }

  return totalBytes;
}

/**
 * Frees the elements of a ZKobject.
 *
 * @param _zKobject The ZKobject to free.
 */
static void freeNs0ZKobjectType(struct hegira_api_ns0_ZKobject *_zKobject) {
  int i;
  if (_zKobject->lowExtreme != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor lowExtreme of type hegira_api_ns0_ZKobject...\n");
#endif
    freeXsStringType(_zKobject->lowExtreme);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor lowExtreme of type hegira_api_ns0_ZKobject...\n");
#endif
    free(_zKobject->lowExtreme);
  }
  if (_zKobject->highExtreme != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor highExtreme of type hegira_api_ns0_ZKobject...\n");
#endif
    freeXsStringType(_zKobject->highExtreme);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor highExtreme of type hegira_api_ns0_ZKobject...\n");
#endif
    free(_zKobject->highExtreme);
  }
  if (_zKobject->id != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor id of type hegira_api_ns0_ZKobject...\n");
#endif
    freeXsStringType(_zKobject->id);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor id of type hegira_api_ns0_ZKobject...\n");
#endif
    free(_zKobject->id);
  }
  if (_zKobject->requestStatus != NULL) {
#if DEBUG_ENUNCIATE > 1
    printf("Freeing type of accessor requestStatus of type hegira_api_ns0_ZKobject...\n");
#endif
    freeNs0StatusType(_zKobject->requestStatus);
#if DEBUG_ENUNCIATE > 1
    printf("Freeing accessor requestStatus of type hegira_api_ns0_ZKobject...\n");
#endif
    free(_zKobject->requestStatus);
  }
}
#endif /* DEF_hegira_api_ns0_ZKobject_M */
