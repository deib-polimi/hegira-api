#import "enunciate-common.h"

@class HEGIRA_APINS0ZKobject;
@class HEGIRA_APINS0Status;

#ifndef DEF_HEGIRA_APINS0ZKobject_H
#define DEF_HEGIRA_APINS0ZKobject_H

/**
 *  @author Marco Scavuzzo


 */
@interface HEGIRA_APINS0ZKobject : NSObject <EnunciateXML>
{
  @private
    NSString *_lowExtreme;
    NSString *_highExtreme;
    NSString *_identifier;
    HEGIRA_APINS0Status *_requestStatus;
}

/**
 * The lower extreme of the generated range of unique ids.
 */
- (NSString *) lowExtreme;

/**
 * The lower extreme of the generated range of unique ids.
 */
- (void) setLowExtreme: (NSString *) newLowExtreme;

/**
 * The highest extreme of the generated range of unique ids.
 */
- (NSString *) highExtreme;

/**
 * The highest extreme of the generated range of unique ids.
 */
- (void) setHighExtreme: (NSString *) newHighExtreme;

/**
 * The generated unique id.
 */
- (NSString *) identifier;

/**
 * The generated unique id.
 */
- (void) setIdentifier: (NSString *) newIdentifier;

/**
 * The status given in response to the REST request.
 */
- (HEGIRA_APINS0Status *) requestStatus;

/**
 * The status given in response to the REST request.
 */
- (void) setRequestStatus: (HEGIRA_APINS0Status *) newRequestStatus;
@end /* interface HEGIRA_APINS0ZKobject */

#endif /* DEF_HEGIRA_APINS0ZKobject_H */
#ifndef DEF_HEGIRA_APINS0Status_H
#define DEF_HEGIRA_APINS0Status_H

/**
 * (no documentation provided)
 */
@interface HEGIRA_APINS0Status : NSObject <EnunciateXML>
{
  @private
    NSString *_State;
    NSString *_message;
    NSString *_error_code;
}

/**
 * OK, WARNING or ERROR.
 */
- (NSString *) State;

/**
 * OK, WARNING or ERROR.
 */
- (void) setState: (NSString *) newState;

/**
 * 
 */
- (NSString *) message;

/**
 * 
 */
- (void) setMessage: (NSString *) newMessage;

/**
 * 
 */
- (NSString *) error_code;

/**
 * 
 */
- (void) setError_code: (NSString *) newError_code;
@end /* interface HEGIRA_APINS0Status */

#endif /* DEF_HEGIRA_APINS0Status_H */
