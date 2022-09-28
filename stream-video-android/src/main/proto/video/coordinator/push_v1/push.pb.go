// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        v3.21.5
// source: video/coordinator/push_v1/push.proto

package push_v1

import (
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	timestamppb "google.golang.org/protobuf/types/known/timestamppb"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

// Keep in sync with stream.config.push.PushProvider
type PushProvider struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Id             string                 `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Disabled       bool                   `protobuf:"varint,2,opt,name=disabled,proto3" json:"disabled,omitempty"`
	DisabledReason string                 `protobuf:"bytes,3,opt,name=disabled_reason,json=disabledReason,proto3" json:"disabled_reason,omitempty"`
	CreatedAt      *timestamppb.Timestamp `protobuf:"bytes,4,opt,name=created_at,json=createdAt,proto3" json:"created_at,omitempty"`
	UpdatedAt      *timestamppb.Timestamp `protobuf:"bytes,5,opt,name=updated_at,json=updatedAt,proto3" json:"updated_at,omitempty"`
	// Types that are assignable to Credentials:
	//
	//	*PushProvider_Apn
	//	*PushProvider_Firebase
	//	*PushProvider_Huawei
	//	*PushProvider_Xiaomi
	Credentials isPushProvider_Credentials `protobuf_oneof:"credentials"`
}

func (x *PushProvider) Reset() {
	*x = PushProvider{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *PushProvider) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*PushProvider) ProtoMessage() {}

func (x *PushProvider) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use PushProvider.ProtoReflect.Descriptor instead.
func (*PushProvider) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{0}
}

func (x *PushProvider) GetId() string {
	if x != nil {
		return x.Id
	}
	return ""
}

func (x *PushProvider) GetDisabled() bool {
	if x != nil {
		return x.Disabled
	}
	return false
}

func (x *PushProvider) GetDisabledReason() string {
	if x != nil {
		return x.DisabledReason
	}
	return ""
}

func (x *PushProvider) GetCreatedAt() *timestamppb.Timestamp {
	if x != nil {
		return x.CreatedAt
	}
	return nil
}

func (x *PushProvider) GetUpdatedAt() *timestamppb.Timestamp {
	if x != nil {
		return x.UpdatedAt
	}
	return nil
}

func (m *PushProvider) GetCredentials() isPushProvider_Credentials {
	if m != nil {
		return m.Credentials
	}
	return nil
}

func (x *PushProvider) GetApn() *ApnCredentials {
	if x, ok := x.GetCredentials().(*PushProvider_Apn); ok {
		return x.Apn
	}
	return nil
}

func (x *PushProvider) GetFirebase() *FirebaseCredentials {
	if x, ok := x.GetCredentials().(*PushProvider_Firebase); ok {
		return x.Firebase
	}
	return nil
}

func (x *PushProvider) GetHuawei() *HuaweiCredentials {
	if x, ok := x.GetCredentials().(*PushProvider_Huawei); ok {
		return x.Huawei
	}
	return nil
}

func (x *PushProvider) GetXiaomi() *XiaomiCredentials {
	if x, ok := x.GetCredentials().(*PushProvider_Xiaomi); ok {
		return x.Xiaomi
	}
	return nil
}

type isPushProvider_Credentials interface {
	isPushProvider_Credentials()
}

type PushProvider_Apn struct {
	Apn *ApnCredentials `protobuf:"bytes,6,opt,name=apn,proto3,oneof"`
}

type PushProvider_Firebase struct {
	Firebase *FirebaseCredentials `protobuf:"bytes,7,opt,name=firebase,proto3,oneof"`
}

type PushProvider_Huawei struct {
	Huawei *HuaweiCredentials `protobuf:"bytes,8,opt,name=huawei,proto3,oneof"`
}

type PushProvider_Xiaomi struct {
	Xiaomi *XiaomiCredentials `protobuf:"bytes,9,opt,name=xiaomi,proto3,oneof"`
}

func (*PushProvider_Apn) isPushProvider_Credentials() {}

func (*PushProvider_Firebase) isPushProvider_Credentials() {}

func (*PushProvider_Huawei) isPushProvider_Credentials() {}

func (*PushProvider_Xiaomi) isPushProvider_Credentials() {}

type ApnCredentials struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	AuthKey     string `protobuf:"bytes,1,opt,name=auth_key,json=authKey,proto3" json:"auth_key,omitempty"`
	KeyId       string `protobuf:"bytes,2,opt,name=key_id,json=keyId,proto3" json:"key_id,omitempty"`
	ApnTopic    string `protobuf:"bytes,3,opt,name=apn_topic,json=apnTopic,proto3" json:"apn_topic,omitempty"`
	TeamId      string `protobuf:"bytes,4,opt,name=team_id,json=teamId,proto3" json:"team_id,omitempty"`
	Development bool   `protobuf:"varint,5,opt,name=development,proto3" json:"development,omitempty"`
}

func (x *ApnCredentials) Reset() {
	*x = ApnCredentials{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *ApnCredentials) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*ApnCredentials) ProtoMessage() {}

func (x *ApnCredentials) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use ApnCredentials.ProtoReflect.Descriptor instead.
func (*ApnCredentials) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{1}
}

func (x *ApnCredentials) GetAuthKey() string {
	if x != nil {
		return x.AuthKey
	}
	return ""
}

func (x *ApnCredentials) GetKeyId() string {
	if x != nil {
		return x.KeyId
	}
	return ""
}

func (x *ApnCredentials) GetApnTopic() string {
	if x != nil {
		return x.ApnTopic
	}
	return ""
}

func (x *ApnCredentials) GetTeamId() string {
	if x != nil {
		return x.TeamId
	}
	return ""
}

func (x *ApnCredentials) GetDevelopment() bool {
	if x != nil {
		return x.Development
	}
	return false
}

type FirebaseCredentials struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	ServerKey       string `protobuf:"bytes,1,opt,name=server_key,json=serverKey,proto3" json:"server_key,omitempty"`
	CredentialsJson string `protobuf:"bytes,2,opt,name=credentials_json,json=credentialsJson,proto3" json:"credentials_json,omitempty"`
}

func (x *FirebaseCredentials) Reset() {
	*x = FirebaseCredentials{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *FirebaseCredentials) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*FirebaseCredentials) ProtoMessage() {}

func (x *FirebaseCredentials) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use FirebaseCredentials.ProtoReflect.Descriptor instead.
func (*FirebaseCredentials) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{2}
}

func (x *FirebaseCredentials) GetServerKey() string {
	if x != nil {
		return x.ServerKey
	}
	return ""
}

func (x *FirebaseCredentials) GetCredentialsJson() string {
	if x != nil {
		return x.CredentialsJson
	}
	return ""
}

type HuaweiCredentials struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Id     string `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	Secret string `protobuf:"bytes,2,opt,name=secret,proto3" json:"secret,omitempty"`
}

func (x *HuaweiCredentials) Reset() {
	*x = HuaweiCredentials{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[3]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *HuaweiCredentials) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*HuaweiCredentials) ProtoMessage() {}

func (x *HuaweiCredentials) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[3]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use HuaweiCredentials.ProtoReflect.Descriptor instead.
func (*HuaweiCredentials) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{3}
}

func (x *HuaweiCredentials) GetId() string {
	if x != nil {
		return x.Id
	}
	return ""
}

func (x *HuaweiCredentials) GetSecret() string {
	if x != nil {
		return x.Secret
	}
	return ""
}

type XiaomiCredentials struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	PackageName string `protobuf:"bytes,1,opt,name=package_name,json=packageName,proto3" json:"package_name,omitempty"`
	Secret      string `protobuf:"bytes,2,opt,name=secret,proto3" json:"secret,omitempty"`
}

func (x *XiaomiCredentials) Reset() {
	*x = XiaomiCredentials{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[4]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *XiaomiCredentials) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*XiaomiCredentials) ProtoMessage() {}

func (x *XiaomiCredentials) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[4]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use XiaomiCredentials.ProtoReflect.Descriptor instead.
func (*XiaomiCredentials) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{4}
}

func (x *XiaomiCredentials) GetPackageName() string {
	if x != nil {
		return x.PackageName
	}
	return ""
}

func (x *XiaomiCredentials) GetSecret() string {
	if x != nil {
		return x.Secret
	}
	return ""
}

type Device struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	UserId           string                 `protobuf:"bytes,1,opt,name=user_id,json=userId,proto3" json:"user_id,omitempty"`
	Id               string                 `protobuf:"bytes,2,opt,name=id,proto3" json:"id,omitempty"`
	Disabled         bool                   `protobuf:"varint,3,opt,name=disabled,proto3" json:"disabled,omitempty"`
	DisabledReason   string                 `protobuf:"bytes,4,opt,name=disabled_reason,json=disabledReason,proto3" json:"disabled_reason,omitempty"`
	PushProviderName string                 `protobuf:"bytes,5,opt,name=push_provider_name,json=pushProviderName,proto3" json:"push_provider_name,omitempty"`
	CreatedAt        *timestamppb.Timestamp `protobuf:"bytes,6,opt,name=created_at,json=createdAt,proto3" json:"created_at,omitempty"`
	UpdatedAt        *timestamppb.Timestamp `protobuf:"bytes,7,opt,name=updated_at,json=updatedAt,proto3" json:"updated_at,omitempty"`
}

func (x *Device) Reset() {
	*x = Device{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[5]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *Device) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*Device) ProtoMessage() {}

func (x *Device) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[5]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use Device.ProtoReflect.Descriptor instead.
func (*Device) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{5}
}

func (x *Device) GetUserId() string {
	if x != nil {
		return x.UserId
	}
	return ""
}

func (x *Device) GetId() string {
	if x != nil {
		return x.Id
	}
	return ""
}

func (x *Device) GetDisabled() bool {
	if x != nil {
		return x.Disabled
	}
	return false
}

func (x *Device) GetDisabledReason() string {
	if x != nil {
		return x.DisabledReason
	}
	return ""
}

func (x *Device) GetPushProviderName() string {
	if x != nil {
		return x.PushProviderName
	}
	return ""
}

func (x *Device) GetCreatedAt() *timestamppb.Timestamp {
	if x != nil {
		return x.CreatedAt
	}
	return nil
}

func (x *Device) GetUpdatedAt() *timestamppb.Timestamp {
	if x != nil {
		return x.UpdatedAt
	}
	return nil
}

// An object that is used in requests to create or update a device
type DeviceInput struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	// Unique device ID, specific to a provider
	Id string `protobuf:"bytes,1,opt,name=id,proto3" json:"id,omitempty"`
	// Push provider ID, as configured in application push notification settings
	// Refers to a set of credentials and templates of one of the supported push providers
	PushProviderId string `protobuf:"bytes,2,opt,name=push_provider_id,json=pushProviderId,proto3" json:"push_provider_id,omitempty"`
}

func (x *DeviceInput) Reset() {
	*x = DeviceInput{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_push_v1_push_proto_msgTypes[6]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *DeviceInput) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*DeviceInput) ProtoMessage() {}

func (x *DeviceInput) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_push_v1_push_proto_msgTypes[6]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use DeviceInput.ProtoReflect.Descriptor instead.
func (*DeviceInput) Descriptor() ([]byte, []int) {
	return file_video_coordinator_push_v1_push_proto_rawDescGZIP(), []int{6}
}

func (x *DeviceInput) GetId() string {
	if x != nil {
		return x.Id
	}
	return ""
}

func (x *DeviceInput) GetPushProviderId() string {
	if x != nil {
		return x.PushProviderId
	}
	return ""
}

var File_video_coordinator_push_v1_push_proto protoreflect.FileDescriptor

var file_video_coordinator_push_v1_push_proto_rawDesc = []byte{
	0x0a, 0x24, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61,
	0x74, 0x6f, 0x72, 0x2f, 0x70, 0x75, 0x73, 0x68, 0x5f, 0x76, 0x31, 0x2f, 0x70, 0x75, 0x73, 0x68,
	0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x12, 0x20, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76,
	0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72,
	0x2e, 0x70, 0x75, 0x73, 0x68, 0x5f, 0x76, 0x31, 0x1a, 0x1f, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65,
	0x2f, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66, 0x2f, 0x74, 0x69, 0x6d, 0x65, 0x73, 0x74,
	0x61, 0x6d, 0x70, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x22, 0xa1, 0x04, 0x0a, 0x0c, 0x50, 0x75,
	0x73, 0x68, 0x50, 0x72, 0x6f, 0x76, 0x69, 0x64, 0x65, 0x72, 0x12, 0x0e, 0x0a, 0x02, 0x69, 0x64,
	0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x02, 0x69, 0x64, 0x12, 0x1a, 0x0a, 0x08, 0x64, 0x69,
	0x73, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x18, 0x02, 0x20, 0x01, 0x28, 0x08, 0x52, 0x08, 0x64, 0x69,
	0x73, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x12, 0x27, 0x0a, 0x0f, 0x64, 0x69, 0x73, 0x61, 0x62, 0x6c,
	0x65, 0x64, 0x5f, 0x72, 0x65, 0x61, 0x73, 0x6f, 0x6e, 0x18, 0x03, 0x20, 0x01, 0x28, 0x09, 0x52,
	0x0e, 0x64, 0x69, 0x73, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x52, 0x65, 0x61, 0x73, 0x6f, 0x6e, 0x12,
	0x39, 0x0a, 0x0a, 0x63, 0x72, 0x65, 0x61, 0x74, 0x65, 0x64, 0x5f, 0x61, 0x74, 0x18, 0x04, 0x20,
	0x01, 0x28, 0x0b, 0x32, 0x1a, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e, 0x54, 0x69, 0x6d, 0x65, 0x73, 0x74, 0x61, 0x6d, 0x70, 0x52,
	0x09, 0x63, 0x72, 0x65, 0x61, 0x74, 0x65, 0x64, 0x41, 0x74, 0x12, 0x39, 0x0a, 0x0a, 0x75, 0x70,
	0x64, 0x61, 0x74, 0x65, 0x64, 0x5f, 0x61, 0x74, 0x18, 0x05, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x1a,
	0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66,
	0x2e, 0x54, 0x69, 0x6d, 0x65, 0x73, 0x74, 0x61, 0x6d, 0x70, 0x52, 0x09, 0x75, 0x70, 0x64, 0x61,
	0x74, 0x65, 0x64, 0x41, 0x74, 0x12, 0x44, 0x0a, 0x03, 0x61, 0x70, 0x6e, 0x18, 0x06, 0x20, 0x01,
	0x28, 0x0b, 0x32, 0x30, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65,
	0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x70, 0x75,
	0x73, 0x68, 0x5f, 0x76, 0x31, 0x2e, 0x41, 0x70, 0x6e, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74,
	0x69, 0x61, 0x6c, 0x73, 0x48, 0x00, 0x52, 0x03, 0x61, 0x70, 0x6e, 0x12, 0x53, 0x0a, 0x08, 0x66,
	0x69, 0x72, 0x65, 0x62, 0x61, 0x73, 0x65, 0x18, 0x07, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x35, 0x2e,
	0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f,
	0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x70, 0x75, 0x73, 0x68, 0x5f, 0x76, 0x31,
	0x2e, 0x46, 0x69, 0x72, 0x65, 0x62, 0x61, 0x73, 0x65, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74,
	0x69, 0x61, 0x6c, 0x73, 0x48, 0x00, 0x52, 0x08, 0x66, 0x69, 0x72, 0x65, 0x62, 0x61, 0x73, 0x65,
	0x12, 0x4d, 0x0a, 0x06, 0x68, 0x75, 0x61, 0x77, 0x65, 0x69, 0x18, 0x08, 0x20, 0x01, 0x28, 0x0b,
	0x32, 0x33, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e,
	0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x70, 0x75, 0x73, 0x68,
	0x5f, 0x76, 0x31, 0x2e, 0x48, 0x75, 0x61, 0x77, 0x65, 0x69, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e,
	0x74, 0x69, 0x61, 0x6c, 0x73, 0x48, 0x00, 0x52, 0x06, 0x68, 0x75, 0x61, 0x77, 0x65, 0x69, 0x12,
	0x4d, 0x0a, 0x06, 0x78, 0x69, 0x61, 0x6f, 0x6d, 0x69, 0x18, 0x09, 0x20, 0x01, 0x28, 0x0b, 0x32,
	0x33, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63,
	0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x70, 0x75, 0x73, 0x68, 0x5f,
	0x76, 0x31, 0x2e, 0x58, 0x69, 0x61, 0x6f, 0x6d, 0x69, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74,
	0x69, 0x61, 0x6c, 0x73, 0x48, 0x00, 0x52, 0x06, 0x78, 0x69, 0x61, 0x6f, 0x6d, 0x69, 0x42, 0x0d,
	0x0a, 0x0b, 0x63, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c, 0x73, 0x22, 0x9a, 0x01,
	0x0a, 0x0e, 0x41, 0x70, 0x6e, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c, 0x73,
	0x12, 0x19, 0x0a, 0x08, 0x61, 0x75, 0x74, 0x68, 0x5f, 0x6b, 0x65, 0x79, 0x18, 0x01, 0x20, 0x01,
	0x28, 0x09, 0x52, 0x07, 0x61, 0x75, 0x74, 0x68, 0x4b, 0x65, 0x79, 0x12, 0x15, 0x0a, 0x06, 0x6b,
	0x65, 0x79, 0x5f, 0x69, 0x64, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x05, 0x6b, 0x65, 0x79,
	0x49, 0x64, 0x12, 0x1b, 0x0a, 0x09, 0x61, 0x70, 0x6e, 0x5f, 0x74, 0x6f, 0x70, 0x69, 0x63, 0x18,
	0x03, 0x20, 0x01, 0x28, 0x09, 0x52, 0x08, 0x61, 0x70, 0x6e, 0x54, 0x6f, 0x70, 0x69, 0x63, 0x12,
	0x17, 0x0a, 0x07, 0x74, 0x65, 0x61, 0x6d, 0x5f, 0x69, 0x64, 0x18, 0x04, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x06, 0x74, 0x65, 0x61, 0x6d, 0x49, 0x64, 0x12, 0x20, 0x0a, 0x0b, 0x64, 0x65, 0x76, 0x65,
	0x6c, 0x6f, 0x70, 0x6d, 0x65, 0x6e, 0x74, 0x18, 0x05, 0x20, 0x01, 0x28, 0x08, 0x52, 0x0b, 0x64,
	0x65, 0x76, 0x65, 0x6c, 0x6f, 0x70, 0x6d, 0x65, 0x6e, 0x74, 0x22, 0x5f, 0x0a, 0x13, 0x46, 0x69,
	0x72, 0x65, 0x62, 0x61, 0x73, 0x65, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c,
	0x73, 0x12, 0x1d, 0x0a, 0x0a, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x5f, 0x6b, 0x65, 0x79, 0x18,
	0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x09, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x4b, 0x65, 0x79,
	0x12, 0x29, 0x0a, 0x10, 0x63, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c, 0x73, 0x5f,
	0x6a, 0x73, 0x6f, 0x6e, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x0f, 0x63, 0x72, 0x65, 0x64,
	0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c, 0x73, 0x4a, 0x73, 0x6f, 0x6e, 0x22, 0x3b, 0x0a, 0x11, 0x48,
	0x75, 0x61, 0x77, 0x65, 0x69, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c, 0x73,
	0x12, 0x0e, 0x0a, 0x02, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x02, 0x69, 0x64,
	0x12, 0x16, 0x0a, 0x06, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x06, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x22, 0x4e, 0x0a, 0x11, 0x58, 0x69, 0x61, 0x6f,
	0x6d, 0x69, 0x43, 0x72, 0x65, 0x64, 0x65, 0x6e, 0x74, 0x69, 0x61, 0x6c, 0x73, 0x12, 0x21, 0x0a,
	0x0c, 0x70, 0x61, 0x63, 0x6b, 0x61, 0x67, 0x65, 0x5f, 0x6e, 0x61, 0x6d, 0x65, 0x18, 0x01, 0x20,
	0x01, 0x28, 0x09, 0x52, 0x0b, 0x70, 0x61, 0x63, 0x6b, 0x61, 0x67, 0x65, 0x4e, 0x61, 0x6d, 0x65,
	0x12, 0x16, 0x0a, 0x06, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x06, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x22, 0x9a, 0x02, 0x0a, 0x06, 0x44, 0x65, 0x76,
	0x69, 0x63, 0x65, 0x12, 0x17, 0x0a, 0x07, 0x75, 0x73, 0x65, 0x72, 0x5f, 0x69, 0x64, 0x18, 0x01,
	0x20, 0x01, 0x28, 0x09, 0x52, 0x06, 0x75, 0x73, 0x65, 0x72, 0x49, 0x64, 0x12, 0x0e, 0x0a, 0x02,
	0x69, 0x64, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x02, 0x69, 0x64, 0x12, 0x1a, 0x0a, 0x08,
	0x64, 0x69, 0x73, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x18, 0x03, 0x20, 0x01, 0x28, 0x08, 0x52, 0x08,
	0x64, 0x69, 0x73, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x12, 0x27, 0x0a, 0x0f, 0x64, 0x69, 0x73, 0x61,
	0x62, 0x6c, 0x65, 0x64, 0x5f, 0x72, 0x65, 0x61, 0x73, 0x6f, 0x6e, 0x18, 0x04, 0x20, 0x01, 0x28,
	0x09, 0x52, 0x0e, 0x64, 0x69, 0x73, 0x61, 0x62, 0x6c, 0x65, 0x64, 0x52, 0x65, 0x61, 0x73, 0x6f,
	0x6e, 0x12, 0x2c, 0x0a, 0x12, 0x70, 0x75, 0x73, 0x68, 0x5f, 0x70, 0x72, 0x6f, 0x76, 0x69, 0x64,
	0x65, 0x72, 0x5f, 0x6e, 0x61, 0x6d, 0x65, 0x18, 0x05, 0x20, 0x01, 0x28, 0x09, 0x52, 0x10, 0x70,
	0x75, 0x73, 0x68, 0x50, 0x72, 0x6f, 0x76, 0x69, 0x64, 0x65, 0x72, 0x4e, 0x61, 0x6d, 0x65, 0x12,
	0x39, 0x0a, 0x0a, 0x63, 0x72, 0x65, 0x61, 0x74, 0x65, 0x64, 0x5f, 0x61, 0x74, 0x18, 0x06, 0x20,
	0x01, 0x28, 0x0b, 0x32, 0x1a, 0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x62, 0x75, 0x66, 0x2e, 0x54, 0x69, 0x6d, 0x65, 0x73, 0x74, 0x61, 0x6d, 0x70, 0x52,
	0x09, 0x63, 0x72, 0x65, 0x61, 0x74, 0x65, 0x64, 0x41, 0x74, 0x12, 0x39, 0x0a, 0x0a, 0x75, 0x70,
	0x64, 0x61, 0x74, 0x65, 0x64, 0x5f, 0x61, 0x74, 0x18, 0x07, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x1a,
	0x2e, 0x67, 0x6f, 0x6f, 0x67, 0x6c, 0x65, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x62, 0x75, 0x66,
	0x2e, 0x54, 0x69, 0x6d, 0x65, 0x73, 0x74, 0x61, 0x6d, 0x70, 0x52, 0x09, 0x75, 0x70, 0x64, 0x61,
	0x74, 0x65, 0x64, 0x41, 0x74, 0x22, 0x47, 0x0a, 0x0b, 0x44, 0x65, 0x76, 0x69, 0x63, 0x65, 0x49,
	0x6e, 0x70, 0x75, 0x74, 0x12, 0x0e, 0x0a, 0x02, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x02, 0x69, 0x64, 0x12, 0x28, 0x0a, 0x10, 0x70, 0x75, 0x73, 0x68, 0x5f, 0x70, 0x72, 0x6f,
	0x76, 0x69, 0x64, 0x65, 0x72, 0x5f, 0x69, 0x64, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x0e,
	0x70, 0x75, 0x73, 0x68, 0x50, 0x72, 0x6f, 0x76, 0x69, 0x64, 0x65, 0x72, 0x49, 0x64, 0x42, 0x09,
	0x5a, 0x07, 0x70, 0x75, 0x73, 0x68, 0x5f, 0x76, 0x31, 0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f,
	0x33,
}

var (
	file_video_coordinator_push_v1_push_proto_rawDescOnce sync.Once
	file_video_coordinator_push_v1_push_proto_rawDescData = file_video_coordinator_push_v1_push_proto_rawDesc
)

func file_video_coordinator_push_v1_push_proto_rawDescGZIP() []byte {
	file_video_coordinator_push_v1_push_proto_rawDescOnce.Do(func() {
		file_video_coordinator_push_v1_push_proto_rawDescData = protoimpl.X.CompressGZIP(file_video_coordinator_push_v1_push_proto_rawDescData)
	})
	return file_video_coordinator_push_v1_push_proto_rawDescData
}

var file_video_coordinator_push_v1_push_proto_msgTypes = make([]protoimpl.MessageInfo, 7)
var file_video_coordinator_push_v1_push_proto_goTypes = []interface{}{
	(*PushProvider)(nil),          // 0: stream.video.coordinator.push_v1.PushProvider
	(*ApnCredentials)(nil),        // 1: stream.video.coordinator.push_v1.ApnCredentials
	(*FirebaseCredentials)(nil),   // 2: stream.video.coordinator.push_v1.FirebaseCredentials
	(*HuaweiCredentials)(nil),     // 3: stream.video.coordinator.push_v1.HuaweiCredentials
	(*XiaomiCredentials)(nil),     // 4: stream.video.coordinator.push_v1.XiaomiCredentials
	(*Device)(nil),                // 5: stream.video.coordinator.push_v1.Device
	(*DeviceInput)(nil),           // 6: stream.video.coordinator.push_v1.DeviceInput
	(*timestamppb.Timestamp)(nil), // 7: google.protobuf.Timestamp
}
var file_video_coordinator_push_v1_push_proto_depIdxs = []int32{
	7, // 0: stream.video.coordinator.push_v1.PushProvider.created_at:type_name -> google.protobuf.Timestamp
	7, // 1: stream.video.coordinator.push_v1.PushProvider.updated_at:type_name -> google.protobuf.Timestamp
	1, // 2: stream.video.coordinator.push_v1.PushProvider.apn:type_name -> stream.video.coordinator.push_v1.ApnCredentials
	2, // 3: stream.video.coordinator.push_v1.PushProvider.firebase:type_name -> stream.video.coordinator.push_v1.FirebaseCredentials
	3, // 4: stream.video.coordinator.push_v1.PushProvider.huawei:type_name -> stream.video.coordinator.push_v1.HuaweiCredentials
	4, // 5: stream.video.coordinator.push_v1.PushProvider.xiaomi:type_name -> stream.video.coordinator.push_v1.XiaomiCredentials
	7, // 6: stream.video.coordinator.push_v1.Device.created_at:type_name -> google.protobuf.Timestamp
	7, // 7: stream.video.coordinator.push_v1.Device.updated_at:type_name -> google.protobuf.Timestamp
	8, // [8:8] is the sub-list for method output_type
	8, // [8:8] is the sub-list for method input_type
	8, // [8:8] is the sub-list for extension type_name
	8, // [8:8] is the sub-list for extension extendee
	0, // [0:8] is the sub-list for field type_name
}

func init() { file_video_coordinator_push_v1_push_proto_init() }
func file_video_coordinator_push_v1_push_proto_init() {
	if File_video_coordinator_push_v1_push_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_video_coordinator_push_v1_push_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*PushProvider); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_video_coordinator_push_v1_push_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*ApnCredentials); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_video_coordinator_push_v1_push_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*FirebaseCredentials); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_video_coordinator_push_v1_push_proto_msgTypes[3].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*HuaweiCredentials); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_video_coordinator_push_v1_push_proto_msgTypes[4].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*XiaomiCredentials); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_video_coordinator_push_v1_push_proto_msgTypes[5].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*Device); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_video_coordinator_push_v1_push_proto_msgTypes[6].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*DeviceInput); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
	}
	file_video_coordinator_push_v1_push_proto_msgTypes[0].OneofWrappers = []interface{}{
		(*PushProvider_Apn)(nil),
		(*PushProvider_Firebase)(nil),
		(*PushProvider_Huawei)(nil),
		(*PushProvider_Xiaomi)(nil),
	}
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_video_coordinator_push_v1_push_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   7,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_video_coordinator_push_v1_push_proto_goTypes,
		DependencyIndexes: file_video_coordinator_push_v1_push_proto_depIdxs,
		MessageInfos:      file_video_coordinator_push_v1_push_proto_msgTypes,
	}.Build()
	File_video_coordinator_push_v1_push_proto = out.File
	file_video_coordinator_push_v1_push_proto_rawDesc = nil
	file_video_coordinator_push_v1_push_proto_goTypes = nil
	file_video_coordinator_push_v1_push_proto_depIdxs = nil
}