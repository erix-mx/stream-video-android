// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        v3.21.5
// source: video/coordinator/event_v1/event.proto

package event_v1

import (
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

type RecordingStarted struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *RecordingStarted) Reset() {
	*x = RecordingStarted{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RecordingStarted) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RecordingStarted) ProtoMessage() {}

func (x *RecordingStarted) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RecordingStarted.ProtoReflect.Descriptor instead.
func (*RecordingStarted) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{0}
}

func (x *RecordingStarted) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type RecordingStopped struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *RecordingStopped) Reset() {
	*x = RecordingStopped{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RecordingStopped) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RecordingStopped) ProtoMessage() {}

func (x *RecordingStopped) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RecordingStopped.ProtoReflect.Descriptor instead.
func (*RecordingStopped) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{1}
}

func (x *RecordingStopped) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type UserUpdated struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	UserId string `protobuf:"bytes,1,opt,name=user_id,json=userId,proto3" json:"user_id,omitempty"`
}

func (x *UserUpdated) Reset() {
	*x = UserUpdated{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *UserUpdated) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*UserUpdated) ProtoMessage() {}

func (x *UserUpdated) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use UserUpdated.ProtoReflect.Descriptor instead.
func (*UserUpdated) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{2}
}

func (x *UserUpdated) GetUserId() string {
	if x != nil {
		return x.UserId
	}
	return ""
}

type BroadcastStarted struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *BroadcastStarted) Reset() {
	*x = BroadcastStarted{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[3]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *BroadcastStarted) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*BroadcastStarted) ProtoMessage() {}

func (x *BroadcastStarted) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[3]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use BroadcastStarted.ProtoReflect.Descriptor instead.
func (*BroadcastStarted) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{3}
}

func (x *BroadcastStarted) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type BroadcastEnded struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *BroadcastEnded) Reset() {
	*x = BroadcastEnded{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[4]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *BroadcastEnded) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*BroadcastEnded) ProtoMessage() {}

func (x *BroadcastEnded) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[4]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use BroadcastEnded.ProtoReflect.Descriptor instead.
func (*BroadcastEnded) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{4}
}

func (x *BroadcastEnded) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallMembersUpdated struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallMembersUpdated) Reset() {
	*x = CallMembersUpdated{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[5]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallMembersUpdated) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallMembersUpdated) ProtoMessage() {}

func (x *CallMembersUpdated) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[5]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallMembersUpdated.ProtoReflect.Descriptor instead.
func (*CallMembersUpdated) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{5}
}

func (x *CallMembersUpdated) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallMembersDeleted struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallMembersDeleted) Reset() {
	*x = CallMembersDeleted{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[6]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallMembersDeleted) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallMembersDeleted) ProtoMessage() {}

func (x *CallMembersDeleted) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[6]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallMembersDeleted.ProtoReflect.Descriptor instead.
func (*CallMembersDeleted) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{6}
}

func (x *CallMembersDeleted) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallCreated struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallCreated) Reset() {
	*x = CallCreated{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[7]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallCreated) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallCreated) ProtoMessage() {}

func (x *CallCreated) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[7]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallCreated.ProtoReflect.Descriptor instead.
func (*CallCreated) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{7}
}

func (x *CallCreated) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallUpdated struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallUpdated) Reset() {
	*x = CallUpdated{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[8]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallUpdated) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallUpdated) ProtoMessage() {}

func (x *CallUpdated) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[8]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallUpdated.ProtoReflect.Descriptor instead.
func (*CallUpdated) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{8}
}

func (x *CallUpdated) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallStarted struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallStarted) Reset() {
	*x = CallStarted{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[9]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallStarted) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallStarted) ProtoMessage() {}

func (x *CallStarted) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[9]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallStarted.ProtoReflect.Descriptor instead.
func (*CallStarted) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{9}
}

func (x *CallStarted) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallEnded struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallEnded) Reset() {
	*x = CallEnded{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[10]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallEnded) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallEnded) ProtoMessage() {}

func (x *CallEnded) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[10]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallEnded.ProtoReflect.Descriptor instead.
func (*CallEnded) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{10}
}

func (x *CallEnded) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

type CallDeleted struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	CallCid string `protobuf:"bytes,1,opt,name=call_cid,json=callCid,proto3" json:"call_cid,omitempty"`
}

func (x *CallDeleted) Reset() {
	*x = CallDeleted{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_event_v1_event_proto_msgTypes[11]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *CallDeleted) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*CallDeleted) ProtoMessage() {}

func (x *CallDeleted) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_event_v1_event_proto_msgTypes[11]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use CallDeleted.ProtoReflect.Descriptor instead.
func (*CallDeleted) Descriptor() ([]byte, []int) {
	return file_video_coordinator_event_v1_event_proto_rawDescGZIP(), []int{11}
}

func (x *CallDeleted) GetCallCid() string {
	if x != nil {
		return x.CallCid
	}
	return ""
}

var File_video_coordinator_event_v1_event_proto protoreflect.FileDescriptor

var file_video_coordinator_event_v1_event_proto_rawDesc = []byte{
	0x0a, 0x26, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61,
	0x74, 0x6f, 0x72, 0x2f, 0x65, 0x76, 0x65, 0x6e, 0x74, 0x5f, 0x76, 0x31, 0x2f, 0x65, 0x76, 0x65,
	0x6e, 0x74, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x12, 0x21, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d,
	0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74,
	0x6f, 0x72, 0x2e, 0x65, 0x76, 0x65, 0x6e, 0x74, 0x5f, 0x76, 0x31, 0x22, 0x2d, 0x0a, 0x10, 0x52,
	0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x53, 0x74, 0x61, 0x72, 0x74, 0x65, 0x64, 0x12,
	0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28,
	0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x2d, 0x0a, 0x10, 0x52, 0x65,
	0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x53, 0x74, 0x6f, 0x70, 0x70, 0x65, 0x64, 0x12, 0x19,
	0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x26, 0x0a, 0x0b, 0x55, 0x73, 0x65,
	0x72, 0x55, 0x70, 0x64, 0x61, 0x74, 0x65, 0x64, 0x12, 0x17, 0x0a, 0x07, 0x75, 0x73, 0x65, 0x72,
	0x5f, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x06, 0x75, 0x73, 0x65, 0x72, 0x49,
	0x64, 0x22, 0x2d, 0x0a, 0x10, 0x42, 0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x53, 0x74,
	0x61, 0x72, 0x74, 0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69,
	0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64,
	0x22, 0x2b, 0x0a, 0x0e, 0x42, 0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x45, 0x6e, 0x64,
	0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18, 0x01,
	0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x2f, 0x0a,
	0x12, 0x43, 0x61, 0x6c, 0x6c, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x73, 0x55, 0x70, 0x64, 0x61,
	0x74, 0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18,
	0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x2f,
	0x0a, 0x12, 0x43, 0x61, 0x6c, 0x6c, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x73, 0x44, 0x65, 0x6c,
	0x65, 0x74, 0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64,
	0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22,
	0x28, 0x0a, 0x0b, 0x43, 0x61, 0x6c, 0x6c, 0x43, 0x72, 0x65, 0x61, 0x74, 0x65, 0x64, 0x12, 0x19,
	0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x28, 0x0a, 0x0b, 0x43, 0x61, 0x6c,
	0x6c, 0x55, 0x70, 0x64, 0x61, 0x74, 0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c,
	0x5f, 0x63, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c,
	0x43, 0x69, 0x64, 0x22, 0x28, 0x0a, 0x0b, 0x43, 0x61, 0x6c, 0x6c, 0x53, 0x74, 0x61, 0x72, 0x74,
	0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18, 0x01,
	0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x26, 0x0a,
	0x09, 0x43, 0x61, 0x6c, 0x6c, 0x45, 0x6e, 0x64, 0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61,
	0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61,
	0x6c, 0x6c, 0x43, 0x69, 0x64, 0x22, 0x28, 0x0a, 0x0b, 0x43, 0x61, 0x6c, 0x6c, 0x44, 0x65, 0x6c,
	0x65, 0x74, 0x65, 0x64, 0x12, 0x19, 0x0a, 0x08, 0x63, 0x61, 0x6c, 0x6c, 0x5f, 0x63, 0x69, 0x64,
	0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x07, 0x63, 0x61, 0x6c, 0x6c, 0x43, 0x69, 0x64, 0x42,
	0x0a, 0x5a, 0x08, 0x65, 0x76, 0x65, 0x6e, 0x74, 0x5f, 0x76, 0x31, 0x62, 0x06, 0x70, 0x72, 0x6f,
	0x74, 0x6f, 0x33,
}

var (
	file_video_coordinator_event_v1_event_proto_rawDescOnce sync.Once
	file_video_coordinator_event_v1_event_proto_rawDescData = file_video_coordinator_event_v1_event_proto_rawDesc
)

func file_video_coordinator_event_v1_event_proto_rawDescGZIP() []byte {
	file_video_coordinator_event_v1_event_proto_rawDescOnce.Do(func() {
		file_video_coordinator_event_v1_event_proto_rawDescData = protoimpl.X.CompressGZIP(file_video_coordinator_event_v1_event_proto_rawDescData)
	})
	return file_video_coordinator_event_v1_event_proto_rawDescData
}

var file_video_coordinator_event_v1_event_proto_msgTypes = make([]protoimpl.MessageInfo, 12)
var file_video_coordinator_event_v1_event_proto_goTypes = []interface{}{
	(*RecordingStarted)(nil),   // 0: stream.video.coordinator.event_v1.RecordingStarted
	(*RecordingStopped)(nil),   // 1: stream.video.coordinator.event_v1.RecordingStopped
	(*UserUpdated)(nil),        // 2: stream.video.coordinator.event_v1.UserUpdated
	(*BroadcastStarted)(nil),   // 3: stream.video.coordinator.event_v1.BroadcastStarted
	(*BroadcastEnded)(nil),     // 4: stream.video.coordinator.event_v1.BroadcastEnded
	(*CallMembersUpdated)(nil), // 5: stream.video.coordinator.event_v1.CallMembersUpdated
	(*CallMembersDeleted)(nil), // 6: stream.video.coordinator.event_v1.CallMembersDeleted
	(*CallCreated)(nil),        // 7: stream.video.coordinator.event_v1.CallCreated
	(*CallUpdated)(nil),        // 8: stream.video.coordinator.event_v1.CallUpdated
	(*CallStarted)(nil),        // 9: stream.video.coordinator.event_v1.CallStarted
	(*CallEnded)(nil),          // 10: stream.video.coordinator.event_v1.CallEnded
	(*CallDeleted)(nil),        // 11: stream.video.coordinator.event_v1.CallDeleted
}
var file_video_coordinator_event_v1_event_proto_depIdxs = []int32{
	0, // [0:0] is the sub-list for method output_type
	0, // [0:0] is the sub-list for method input_type
	0, // [0:0] is the sub-list for extension type_name
	0, // [0:0] is the sub-list for extension extendee
	0, // [0:0] is the sub-list for field type_name
}

func init() { file_video_coordinator_event_v1_event_proto_init() }
func file_video_coordinator_event_v1_event_proto_init() {
	if File_video_coordinator_event_v1_event_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_video_coordinator_event_v1_event_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RecordingStarted); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RecordingStopped); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*UserUpdated); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[3].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*BroadcastStarted); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[4].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*BroadcastEnded); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[5].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallMembersUpdated); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[6].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallMembersDeleted); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[7].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallCreated); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[8].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallUpdated); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[9].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallStarted); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[10].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallEnded); i {
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
		file_video_coordinator_event_v1_event_proto_msgTypes[11].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*CallDeleted); i {
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
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_video_coordinator_event_v1_event_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   12,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_video_coordinator_event_v1_event_proto_goTypes,
		DependencyIndexes: file_video_coordinator_event_v1_event_proto_depIdxs,
		MessageInfos:      file_video_coordinator_event_v1_event_proto_msgTypes,
	}.Build()
	File_video_coordinator_event_v1_event_proto = out.File
	file_video_coordinator_event_v1_event_proto_rawDesc = nil
	file_video_coordinator_event_v1_event_proto_goTypes = nil
	file_video_coordinator_event_v1_event_proto_depIdxs = nil
}