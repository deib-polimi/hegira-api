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
 * (no documentation provided)
 */
- (NSString *) lowExtreme;

/**
 * (no documentation provided)
 */
- (void) setLowExtreme: (NSString *) newLowExtreme;

/**
 * (no documentation provided)
 */
- (NSString *) highExtreme;

/**
 * (no documentation provided)
 */
- (void) setHighExtreme: (NSString *) newHighExtreme;

/**
 * (no documentation provided)
 */
- (NSString *) identifier;

/**
 * (no documentation provided)
 */
- (void) setIdentifier: (NSString *) newIdentifier;

/**
 * (no documentation provided)
 */
- (HEGIRA_APINS0Status *) requestStatus;

/**
 * (no documentation provided)
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
 * (no documentation provided)
 */
- (NSString *) State;

/**
 * (no documentation provided)
 */
- (void) setState: (NSString *) newState;

/**
 * (no documentation provided)
 */
- (NSString *) message;

/**
 * (no documentation provided)
 */
- (void) setMessage: (NSString *) newMessage;

/**
 * (no documentation provided)
 */
- (NSString *) error_code;

/**
 * (no documentation provided)
 */
- (void) setError_code: (NSString *) newError_code;
@end /* interface HEGIRA_APINS0Status */

#endif /* DEF_HEGIRA_APINS0Status_H */
