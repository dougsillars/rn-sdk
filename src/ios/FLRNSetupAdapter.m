//
//  FLRNSetupAdapter.m
//  Fidel
//
//  Created by Corneliu on 22/04/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "FLRNSetupAdapter.h"
#import "FLRNSDKSetupOptions.h"
#import "Fidel-Swift.h"

@implementation FLRNSetupAdapter

NSString *const kApiKeyDictionaryKey = @"apiKey";
NSString *const kProgramIDKey = @"programId";
NSString *const kSetupOptionKey = @"SetupOption";

- (NSDictionary *)constantsToExport {
    return @{kSetupOptionKey: @{
                     kApiKeyDictionaryKey: @(FLSDKSetupOptionApiKey),
                     kProgramIDKey: @(FLSDKSetupOptionProgramID)
                     }
             };
}

- (void)setupWith:(NSDictionary *)setupInfo {
    FLFidel.apiKey = [self getStringValueFor:kApiKeyDictionaryKey fromDictionary:setupInfo];
    FLFidel.programId = [self getStringValueFor:kProgramIDKey fromDictionary:setupInfo];
}

- (NSString * _Nullable)getStringValueFor:(NSString *)key fromDictionary:(NSDictionary *)dict {
    NSArray *allKeys = dict.allKeys;
    if ([allKeys containsObject:key]) {
        id value = dict[key];
        if ([value isKindOfClass:[NSString class]]) {
            return value;
        }
    }
    return nil;
}

@end
