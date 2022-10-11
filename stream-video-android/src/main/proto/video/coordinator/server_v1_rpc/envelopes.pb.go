// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        v3.21.5
// source: video/coordinator/server_v1_rpc/envelopes.proto

package server_v1_rpc

import (
	call_v1 "github.com/GetStream/video-proto/protobuf/video/coordinator/call_v1"
	member_v1 "github.com/GetStream/video-proto/protobuf/video/coordinator/member_v1"
	recording_v1 "github.com/GetStream/video-proto/protobuf/video/coordinator/recording_v1"
	user_v1 "github.com/GetStream/video-proto/protobuf/video/coordinator/user_v1"
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

// CallsEnvelope contains list of members and all related information to them
// Only used in response types that return list of members
type ExportUserEnvelope struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	User  *user_v1.User   `protobuf:"bytes,1,opt,name=user,proto3" json:"user,omitempty"`
	Calls []*call_v1.Call `protobuf:"bytes,2,rep,name=calls,proto3" json:"calls,omitempty"`
	// Map of members indexed by Call.call_cid
	Members    map[string]*member_v1.Member `protobuf:"bytes,3,rep,name=members,proto3" json:"members,omitempty" protobuf_key:"bytes,1,opt,name=key,proto3" protobuf_val:"bytes,2,opt,name=value,proto3"`
	Recordings []*recording_v1.Recording    `protobuf:"bytes,4,rep,name=recordings,proto3" json:"recordings,omitempty"`
}

func (x *ExportUserEnvelope) Reset() {
	*x = ExportUserEnvelope{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_server_v1_rpc_envelopes_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *ExportUserEnvelope) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*ExportUserEnvelope) ProtoMessage() {}

func (x *ExportUserEnvelope) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_server_v1_rpc_envelopes_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use ExportUserEnvelope.ProtoReflect.Descriptor instead.
func (*ExportUserEnvelope) Descriptor() ([]byte, []int) {
	return file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescGZIP(), []int{0}
}

func (x *ExportUserEnvelope) GetUser() *user_v1.User {
	if x != nil {
		return x.User
	}
	return nil
}

func (x *ExportUserEnvelope) GetCalls() []*call_v1.Call {
	if x != nil {
		return x.Calls
	}
	return nil
}

func (x *ExportUserEnvelope) GetMembers() map[string]*member_v1.Member {
	if x != nil {
		return x.Members
	}
	return nil
}

func (x *ExportUserEnvelope) GetRecordings() []*recording_v1.Recording {
	if x != nil {
		return x.Recordings
	}
	return nil
}

var File_video_coordinator_server_v1_rpc_envelopes_proto protoreflect.FileDescriptor

var file_video_coordinator_server_v1_rpc_envelopes_proto_rawDesc = []byte{
	0x0a, 0x2f, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61,
	0x74, 0x6f, 0x72, 0x2f, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x5f, 0x72, 0x70,
	0x63, 0x2f, 0x65, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 0x70, 0x65, 0x73, 0x2e, 0x70, 0x72, 0x6f, 0x74,
	0x6f, 0x12, 0x26, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e,
	0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x73, 0x65, 0x72, 0x76,
	0x65, 0x72, 0x5f, 0x76, 0x31, 0x5f, 0x72, 0x70, 0x63, 0x1a, 0x28, 0x76, 0x69, 0x64, 0x65, 0x6f,
	0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2f, 0x6d, 0x65, 0x6d,
	0x62, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x2f, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x2e, 0x70, 0x72,
	0x6f, 0x74, 0x6f, 0x1a, 0x24, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64,
	0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2f, 0x75, 0x73, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x2f, 0x75,
	0x73, 0x65, 0x72, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x1a, 0x24, 0x76, 0x69, 0x64, 0x65, 0x6f,
	0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2f, 0x63, 0x61, 0x6c,
	0x6c, 0x5f, 0x76, 0x31, 0x2f, 0x63, 0x61, 0x6c, 0x6c, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x1a,
	0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74,
	0x6f, 0x72, 0x2f, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x5f, 0x76, 0x31, 0x2f,
	0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x22,
	0xab, 0x03, 0x0a, 0x12, 0x45, 0x78, 0x70, 0x6f, 0x72, 0x74, 0x55, 0x73, 0x65, 0x72, 0x45, 0x6e,
	0x76, 0x65, 0x6c, 0x6f, 0x70, 0x65, 0x12, 0x3a, 0x0a, 0x04, 0x75, 0x73, 0x65, 0x72, 0x18, 0x01,
	0x20, 0x01, 0x28, 0x0b, 0x32, 0x26, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69,
	0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e,
	0x75, 0x73, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x2e, 0x55, 0x73, 0x65, 0x72, 0x52, 0x04, 0x75, 0x73,
	0x65, 0x72, 0x12, 0x3c, 0x0a, 0x05, 0x63, 0x61, 0x6c, 0x6c, 0x73, 0x18, 0x02, 0x20, 0x03, 0x28,
	0x0b, 0x32, 0x26, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f,
	0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x63, 0x61, 0x6c,
	0x6c, 0x5f, 0x76, 0x31, 0x2e, 0x43, 0x61, 0x6c, 0x6c, 0x52, 0x05, 0x63, 0x61, 0x6c, 0x6c, 0x73,
	0x12, 0x61, 0x0a, 0x07, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x73, 0x18, 0x03, 0x20, 0x03, 0x28,
	0x0b, 0x32, 0x47, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f,
	0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x73, 0x65, 0x72,
	0x76, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x5f, 0x72, 0x70, 0x63, 0x2e, 0x45, 0x78, 0x70, 0x6f, 0x72,
	0x74, 0x55, 0x73, 0x65, 0x72, 0x45, 0x6e, 0x76, 0x65, 0x6c, 0x6f, 0x70, 0x65, 0x2e, 0x4d, 0x65,
	0x6d, 0x62, 0x65, 0x72, 0x73, 0x45, 0x6e, 0x74, 0x72, 0x79, 0x52, 0x07, 0x6d, 0x65, 0x6d, 0x62,
	0x65, 0x72, 0x73, 0x12, 0x50, 0x0a, 0x0a, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67,
	0x73, 0x18, 0x04, 0x20, 0x03, 0x28, 0x0b, 0x32, 0x30, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d,
	0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74,
	0x6f, 0x72, 0x2e, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x5f, 0x76, 0x31, 0x2e,
	0x52, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x52, 0x0a, 0x72, 0x65, 0x63, 0x6f, 0x72,
	0x64, 0x69, 0x6e, 0x67, 0x73, 0x1a, 0x66, 0x0a, 0x0c, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x73,
	0x45, 0x6e, 0x74, 0x72, 0x79, 0x12, 0x10, 0x0a, 0x03, 0x6b, 0x65, 0x79, 0x18, 0x01, 0x20, 0x01,
	0x28, 0x09, 0x52, 0x03, 0x6b, 0x65, 0x79, 0x12, 0x40, 0x0a, 0x05, 0x76, 0x61, 0x6c, 0x75, 0x65,
	0x18, 0x02, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x2a, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e,
	0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f,
	0x72, 0x2e, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x2e, 0x4d, 0x65, 0x6d, 0x62,
	0x65, 0x72, 0x52, 0x05, 0x76, 0x61, 0x6c, 0x75, 0x65, 0x3a, 0x02, 0x38, 0x01, 0x42, 0x0f, 0x5a,
	0x0d, 0x73, 0x65, 0x72, 0x76, 0x65, 0x72, 0x5f, 0x76, 0x31, 0x5f, 0x72, 0x70, 0x63, 0x62, 0x06,
	0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescOnce sync.Once
	file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescData = file_video_coordinator_server_v1_rpc_envelopes_proto_rawDesc
)

func file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescGZIP() []byte {
	file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescOnce.Do(func() {
		file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescData = protoimpl.X.CompressGZIP(file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescData)
	})
	return file_video_coordinator_server_v1_rpc_envelopes_proto_rawDescData
}

var file_video_coordinator_server_v1_rpc_envelopes_proto_msgTypes = make([]protoimpl.MessageInfo, 2)
var file_video_coordinator_server_v1_rpc_envelopes_proto_goTypes = []interface{}{
	(*ExportUserEnvelope)(nil),     // 0: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope
	nil,                            // 1: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.MembersEntry
	(*user_v1.User)(nil),           // 2: stream.video.coordinator.user_v1.User
	(*call_v1.Call)(nil),           // 3: stream.video.coordinator.call_v1.Call
	(*recording_v1.Recording)(nil), // 4: stream.video.coordinator.recording_v1.Recording
	(*member_v1.Member)(nil),       // 5: stream.video.coordinator.member_v1.Member
}
var file_video_coordinator_server_v1_rpc_envelopes_proto_depIdxs = []int32{
	2, // 0: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.user:type_name -> stream.video.coordinator.user_v1.User
	3, // 1: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.calls:type_name -> stream.video.coordinator.call_v1.Call
	1, // 2: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.members:type_name -> stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.MembersEntry
	4, // 3: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.recordings:type_name -> stream.video.coordinator.recording_v1.Recording
	5, // 4: stream.video.coordinator.server_v1_rpc.ExportUserEnvelope.MembersEntry.value:type_name -> stream.video.coordinator.member_v1.Member
	5, // [5:5] is the sub-list for method output_type
	5, // [5:5] is the sub-list for method input_type
	5, // [5:5] is the sub-list for extension type_name
	5, // [5:5] is the sub-list for extension extendee
	0, // [0:5] is the sub-list for field type_name
}

func init() { file_video_coordinator_server_v1_rpc_envelopes_proto_init() }
func file_video_coordinator_server_v1_rpc_envelopes_proto_init() {
	if File_video_coordinator_server_v1_rpc_envelopes_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_video_coordinator_server_v1_rpc_envelopes_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*ExportUserEnvelope); i {
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
			RawDescriptor: file_video_coordinator_server_v1_rpc_envelopes_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   2,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_video_coordinator_server_v1_rpc_envelopes_proto_goTypes,
		DependencyIndexes: file_video_coordinator_server_v1_rpc_envelopes_proto_depIdxs,
		MessageInfos:      file_video_coordinator_server_v1_rpc_envelopes_proto_msgTypes,
	}.Build()
	File_video_coordinator_server_v1_rpc_envelopes_proto = out.File
	file_video_coordinator_server_v1_rpc_envelopes_proto_rawDesc = nil
	file_video_coordinator_server_v1_rpc_envelopes_proto_goTypes = nil
	file_video_coordinator_server_v1_rpc_envelopes_proto_depIdxs = nil
}
