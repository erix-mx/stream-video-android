// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.1
// 	protoc        v3.21.5
// source: video/coordinator/broadcast_v1/broadcast.proto

package broadcast_v1

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

type Codec int32

const (
	Codec_CODEC_H264_UNSPECIFIED Codec = 0
	Codec_CODEC_VP8              Codec = 1
	Codec_CODEC_VP9              Codec = 2
)

// Enum value maps for Codec.
var (
	Codec_name = map[int32]string{
		0: "CODEC_H264_UNSPECIFIED",
		1: "CODEC_VP8",
		2: "CODEC_VP9",
	}
	Codec_value = map[string]int32{
		"CODEC_H264_UNSPECIFIED": 0,
		"CODEC_VP8":              1,
		"CODEC_VP9":              2,
	}
)

func (x Codec) Enum() *Codec {
	p := new(Codec)
	*p = x
	return p
}

func (x Codec) String() string {
	return protoimpl.X.EnumStringOf(x.Descriptor(), protoreflect.EnumNumber(x))
}

func (Codec) Descriptor() protoreflect.EnumDescriptor {
	return file_video_coordinator_broadcast_v1_broadcast_proto_enumTypes[0].Descriptor()
}

func (Codec) Type() protoreflect.EnumType {
	return &file_video_coordinator_broadcast_v1_broadcast_proto_enumTypes[0]
}

func (x Codec) Number() protoreflect.EnumNumber {
	return protoreflect.EnumNumber(x)
}

// Deprecated: Use Codec.Descriptor instead.
func (Codec) EnumDescriptor() ([]byte, []int) {
	return file_video_coordinator_broadcast_v1_broadcast_proto_rawDescGZIP(), []int{0}
}

type RTMPOptions struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Urls []string `protobuf:"bytes,1,rep,name=urls,proto3" json:"urls,omitempty"`
}

func (x *RTMPOptions) Reset() {
	*x = RTMPOptions{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RTMPOptions) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RTMPOptions) ProtoMessage() {}

func (x *RTMPOptions) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RTMPOptions.ProtoReflect.Descriptor instead.
func (*RTMPOptions) Descriptor() ([]byte, []int) {
	return file_video_coordinator_broadcast_v1_broadcast_proto_rawDescGZIP(), []int{0}
}

func (x *RTMPOptions) GetUrls() []string {
	if x != nil {
		return x.Urls
	}
	return nil
}

type Broadcast struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Rtmp   *RTMPOptions `protobuf:"bytes,1,opt,name=rtmp,proto3" json:"rtmp,omitempty"`
	HlsUrl string       `protobuf:"bytes,2,opt,name=hls_url,json=hlsUrl,proto3" json:"hls_url,omitempty"`
}

func (x *Broadcast) Reset() {
	*x = Broadcast{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *Broadcast) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*Broadcast) ProtoMessage() {}

func (x *Broadcast) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use Broadcast.ProtoReflect.Descriptor instead.
func (*Broadcast) Descriptor() ([]byte, []int) {
	return file_video_coordinator_broadcast_v1_broadcast_proto_rawDescGZIP(), []int{1}
}

func (x *Broadcast) GetRtmp() *RTMPOptions {
	if x != nil {
		return x.Rtmp
	}
	return nil
}

func (x *Broadcast) GetHlsUrl() string {
	if x != nil {
		return x.HlsUrl
	}
	return ""
}

var File_video_coordinator_broadcast_v1_broadcast_proto protoreflect.FileDescriptor

var file_video_coordinator_broadcast_v1_broadcast_proto_rawDesc = []byte{
	0x0a, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61,
	0x74, 0x6f, 0x72, 0x2f, 0x62, 0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x5f, 0x76, 0x31,
	0x2f, 0x62, 0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f,
	0x12, 0x25, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63,
	0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x62, 0x72, 0x6f, 0x61, 0x64,
	0x63, 0x61, 0x73, 0x74, 0x5f, 0x76, 0x31, 0x22, 0x21, 0x0a, 0x0b, 0x52, 0x54, 0x4d, 0x50, 0x4f,
	0x70, 0x74, 0x69, 0x6f, 0x6e, 0x73, 0x12, 0x12, 0x0a, 0x04, 0x75, 0x72, 0x6c, 0x73, 0x18, 0x01,
	0x20, 0x03, 0x28, 0x09, 0x52, 0x04, 0x75, 0x72, 0x6c, 0x73, 0x22, 0x6c, 0x0a, 0x09, 0x42, 0x72,
	0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x12, 0x46, 0x0a, 0x04, 0x72, 0x74, 0x6d, 0x70, 0x18,
	0x01, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x32, 0x2e, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76,
	0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72,
	0x2e, 0x62, 0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x5f, 0x76, 0x31, 0x2e, 0x52, 0x54,
	0x4d, 0x50, 0x4f, 0x70, 0x74, 0x69, 0x6f, 0x6e, 0x73, 0x52, 0x04, 0x72, 0x74, 0x6d, 0x70, 0x12,
	0x17, 0x0a, 0x07, 0x68, 0x6c, 0x73, 0x5f, 0x75, 0x72, 0x6c, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x06, 0x68, 0x6c, 0x73, 0x55, 0x72, 0x6c, 0x2a, 0x41, 0x0a, 0x05, 0x43, 0x6f, 0x64, 0x65,
	0x63, 0x12, 0x1a, 0x0a, 0x16, 0x43, 0x4f, 0x44, 0x45, 0x43, 0x5f, 0x48, 0x32, 0x36, 0x34, 0x5f,
	0x55, 0x4e, 0x53, 0x50, 0x45, 0x43, 0x49, 0x46, 0x49, 0x45, 0x44, 0x10, 0x00, 0x12, 0x0d, 0x0a,
	0x09, 0x43, 0x4f, 0x44, 0x45, 0x43, 0x5f, 0x56, 0x50, 0x38, 0x10, 0x01, 0x12, 0x0d, 0x0a, 0x09,
	0x43, 0x4f, 0x44, 0x45, 0x43, 0x5f, 0x56, 0x50, 0x39, 0x10, 0x02, 0x42, 0x3b, 0x42, 0x0b, 0x42,
	0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x56, 0x31, 0x50, 0x01, 0x5a, 0x0c, 0x62, 0x72,
	0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x5f, 0x76, 0x31, 0xaa, 0x02, 0x1b, 0x53, 0x74, 0x72,
	0x65, 0x61, 0x6d, 0x2e, 0x56, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x76, 0x31, 0x2e, 0x43, 0x6f, 0x6f,
	0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_video_coordinator_broadcast_v1_broadcast_proto_rawDescOnce sync.Once
	file_video_coordinator_broadcast_v1_broadcast_proto_rawDescData = file_video_coordinator_broadcast_v1_broadcast_proto_rawDesc
)

func file_video_coordinator_broadcast_v1_broadcast_proto_rawDescGZIP() []byte {
	file_video_coordinator_broadcast_v1_broadcast_proto_rawDescOnce.Do(func() {
		file_video_coordinator_broadcast_v1_broadcast_proto_rawDescData = protoimpl.X.CompressGZIP(file_video_coordinator_broadcast_v1_broadcast_proto_rawDescData)
	})
	return file_video_coordinator_broadcast_v1_broadcast_proto_rawDescData
}

var file_video_coordinator_broadcast_v1_broadcast_proto_enumTypes = make([]protoimpl.EnumInfo, 1)
var file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes = make([]protoimpl.MessageInfo, 2)
var file_video_coordinator_broadcast_v1_broadcast_proto_goTypes = []interface{}{
	(Codec)(0),          // 0: stream.video.coordinator.broadcast_v1.Codec
	(*RTMPOptions)(nil), // 1: stream.video.coordinator.broadcast_v1.RTMPOptions
	(*Broadcast)(nil),   // 2: stream.video.coordinator.broadcast_v1.Broadcast
}
var file_video_coordinator_broadcast_v1_broadcast_proto_depIdxs = []int32{
	1, // 0: stream.video.coordinator.broadcast_v1.Broadcast.rtmp:type_name -> stream.video.coordinator.broadcast_v1.RTMPOptions
	1, // [1:1] is the sub-list for method output_type
	1, // [1:1] is the sub-list for method input_type
	1, // [1:1] is the sub-list for extension type_name
	1, // [1:1] is the sub-list for extension extendee
	0, // [0:1] is the sub-list for field type_name
}

func init() { file_video_coordinator_broadcast_v1_broadcast_proto_init() }
func file_video_coordinator_broadcast_v1_broadcast_proto_init() {
	if File_video_coordinator_broadcast_v1_broadcast_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RTMPOptions); i {
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
		file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*Broadcast); i {
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
			RawDescriptor: file_video_coordinator_broadcast_v1_broadcast_proto_rawDesc,
			NumEnums:      1,
			NumMessages:   2,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_video_coordinator_broadcast_v1_broadcast_proto_goTypes,
		DependencyIndexes: file_video_coordinator_broadcast_v1_broadcast_proto_depIdxs,
		EnumInfos:         file_video_coordinator_broadcast_v1_broadcast_proto_enumTypes,
		MessageInfos:      file_video_coordinator_broadcast_v1_broadcast_proto_msgTypes,
	}.Build()
	File_video_coordinator_broadcast_v1_broadcast_proto = out.File
	file_video_coordinator_broadcast_v1_broadcast_proto_rawDesc = nil
	file_video_coordinator_broadcast_v1_broadcast_proto_goTypes = nil
	file_video_coordinator_broadcast_v1_broadcast_proto_depIdxs = nil
}