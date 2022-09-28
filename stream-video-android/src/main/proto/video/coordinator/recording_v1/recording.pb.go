// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        v3.21.5
// source: video/coordinator/recording_v1/recording.proto

package recording_v1

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

type RecordingStorage int32

const (
	RecordingStorage_RECORDING_STORAGE_S3_UNSPECIFIED RecordingStorage = 0
)

// Enum value maps for RecordingStorage.
var (
	RecordingStorage_name = map[int32]string{
		0: "RECORDING_STORAGE_S3_UNSPECIFIED",
	}
	RecordingStorage_value = map[string]int32{
		"RECORDING_STORAGE_S3_UNSPECIFIED": 0,
	}
)

func (x RecordingStorage) Enum() *RecordingStorage {
	p := new(RecordingStorage)
	*p = x
	return p
}

func (x RecordingStorage) String() string {
	return protoimpl.X.EnumStringOf(x.Descriptor(), protoreflect.EnumNumber(x))
}

func (RecordingStorage) Descriptor() protoreflect.EnumDescriptor {
	return file_video_coordinator_recording_v1_recording_proto_enumTypes[0].Descriptor()
}

func (RecordingStorage) Type() protoreflect.EnumType {
	return &file_video_coordinator_recording_v1_recording_proto_enumTypes[0]
}

func (x RecordingStorage) Number() protoreflect.EnumNumber {
	return protoreflect.EnumNumber(x)
}

// Deprecated: Use RecordingStorage.Descriptor instead.
func (RecordingStorage) EnumDescriptor() ([]byte, []int) {
	return file_video_coordinator_recording_v1_recording_proto_rawDescGZIP(), []int{0}
}

type File struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Type      string `protobuf:"bytes,1,opt,name=type,proto3" json:"type,omitempty"`
	Composite bool   `protobuf:"varint,2,opt,name=composite,proto3" json:"composite,omitempty"`
	UserId    string `protobuf:"bytes,3,opt,name=user_id,json=userId,proto3" json:"user_id,omitempty"`
	Url       string `protobuf:"bytes,4,opt,name=url,proto3" json:"url,omitempty"`
}

func (x *File) Reset() {
	*x = File{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_recording_v1_recording_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *File) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*File) ProtoMessage() {}

func (x *File) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_recording_v1_recording_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use File.ProtoReflect.Descriptor instead.
func (*File) Descriptor() ([]byte, []int) {
	return file_video_coordinator_recording_v1_recording_proto_rawDescGZIP(), []int{0}
}

func (x *File) GetType() string {
	if x != nil {
		return x.Type
	}
	return ""
}

func (x *File) GetComposite() bool {
	if x != nil {
		return x.Composite
	}
	return false
}

func (x *File) GetUserId() string {
	if x != nil {
		return x.UserId
	}
	return ""
}

func (x *File) GetUrl() string {
	if x != nil {
		return x.Url
	}
	return ""
}

type RecordBroadcast struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	// If true merge all audio and video, if false split them.
	Composite bool `protobuf:"varint,1,opt,name=composite,proto3" json:"composite,omitempty"`
	// List of recorded files.
	Files []*File `protobuf:"bytes,2,rep,name=files,proto3" json:"files,omitempty"`
}

func (x *RecordBroadcast) Reset() {
	*x = RecordBroadcast{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_recording_v1_recording_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RecordBroadcast) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RecordBroadcast) ProtoMessage() {}

func (x *RecordBroadcast) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_recording_v1_recording_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RecordBroadcast.ProtoReflect.Descriptor instead.
func (*RecordBroadcast) Descriptor() ([]byte, []int) {
	return file_video_coordinator_recording_v1_recording_proto_rawDescGZIP(), []int{1}
}

func (x *RecordBroadcast) GetComposite() bool {
	if x != nil {
		return x.Composite
	}
	return false
}

func (x *RecordBroadcast) GetFiles() []*File {
	if x != nil {
		return x.Files
	}
	return nil
}

type RecordingStorageOptions struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Storage    RecordingStorage `protobuf:"varint,1,opt,name=storage,proto3,enum=stream.video.coordinator.recording_v1.RecordingStorage" json:"storage,omitempty"`
	AccessKey  string           `protobuf:"bytes,2,opt,name=access_key,json=accessKey,proto3" json:"access_key,omitempty"`
	SecretKey  string           `protobuf:"bytes,3,opt,name=secret_key,json=secretKey,proto3" json:"secret_key,omitempty"`
	BucketName string           `protobuf:"bytes,4,opt,name=bucket_name,json=bucketName,proto3" json:"bucket_name,omitempty"`
	Region     string           `protobuf:"bytes,5,opt,name=region,proto3" json:"region,omitempty"`
	Path       string           `protobuf:"bytes,6,opt,name=path,proto3" json:"path,omitempty"`
}

func (x *RecordingStorageOptions) Reset() {
	*x = RecordingStorageOptions{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_recording_v1_recording_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *RecordingStorageOptions) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*RecordingStorageOptions) ProtoMessage() {}

func (x *RecordingStorageOptions) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_recording_v1_recording_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use RecordingStorageOptions.ProtoReflect.Descriptor instead.
func (*RecordingStorageOptions) Descriptor() ([]byte, []int) {
	return file_video_coordinator_recording_v1_recording_proto_rawDescGZIP(), []int{2}
}

func (x *RecordingStorageOptions) GetStorage() RecordingStorage {
	if x != nil {
		return x.Storage
	}
	return RecordingStorage_RECORDING_STORAGE_S3_UNSPECIFIED
}

func (x *RecordingStorageOptions) GetAccessKey() string {
	if x != nil {
		return x.AccessKey
	}
	return ""
}

func (x *RecordingStorageOptions) GetSecretKey() string {
	if x != nil {
		return x.SecretKey
	}
	return ""
}

func (x *RecordingStorageOptions) GetBucketName() string {
	if x != nil {
		return x.BucketName
	}
	return ""
}

func (x *RecordingStorageOptions) GetRegion() string {
	if x != nil {
		return x.Region
	}
	return ""
}

func (x *RecordingStorageOptions) GetPath() string {
	if x != nil {
		return x.Path
	}
	return ""
}

var File_video_coordinator_recording_v1_recording_proto protoreflect.FileDescriptor

var file_video_coordinator_recording_v1_recording_proto_rawDesc = []byte{
	0x0a, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61,
	0x74, 0x6f, 0x72, 0x2f, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x5f, 0x76, 0x31,
	0x2f, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f,
	0x12, 0x25, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63,
	0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x72, 0x65, 0x63, 0x6f, 0x72,
	0x64, 0x69, 0x6e, 0x67, 0x5f, 0x76, 0x31, 0x22, 0x63, 0x0a, 0x04, 0x46, 0x69, 0x6c, 0x65, 0x12,
	0x12, 0x0a, 0x04, 0x74, 0x79, 0x70, 0x65, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x04, 0x74,
	0x79, 0x70, 0x65, 0x12, 0x1c, 0x0a, 0x09, 0x63, 0x6f, 0x6d, 0x70, 0x6f, 0x73, 0x69, 0x74, 0x65,
	0x18, 0x02, 0x20, 0x01, 0x28, 0x08, 0x52, 0x09, 0x63, 0x6f, 0x6d, 0x70, 0x6f, 0x73, 0x69, 0x74,
	0x65, 0x12, 0x17, 0x0a, 0x07, 0x75, 0x73, 0x65, 0x72, 0x5f, 0x69, 0x64, 0x18, 0x03, 0x20, 0x01,
	0x28, 0x09, 0x52, 0x06, 0x75, 0x73, 0x65, 0x72, 0x49, 0x64, 0x12, 0x10, 0x0a, 0x03, 0x75, 0x72,
	0x6c, 0x18, 0x04, 0x20, 0x01, 0x28, 0x09, 0x52, 0x03, 0x75, 0x72, 0x6c, 0x22, 0x72, 0x0a, 0x0f,
	0x52, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x42, 0x72, 0x6f, 0x61, 0x64, 0x63, 0x61, 0x73, 0x74, 0x12,
	0x1c, 0x0a, 0x09, 0x63, 0x6f, 0x6d, 0x70, 0x6f, 0x73, 0x69, 0x74, 0x65, 0x18, 0x01, 0x20, 0x01,
	0x28, 0x08, 0x52, 0x09, 0x63, 0x6f, 0x6d, 0x70, 0x6f, 0x73, 0x69, 0x74, 0x65, 0x12, 0x41, 0x0a,
	0x05, 0x66, 0x69, 0x6c, 0x65, 0x73, 0x18, 0x02, 0x20, 0x03, 0x28, 0x0b, 0x32, 0x2b, 0x2e, 0x73,
	0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72,
	0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e,
	0x67, 0x5f, 0x76, 0x31, 0x2e, 0x46, 0x69, 0x6c, 0x65, 0x52, 0x05, 0x66, 0x69, 0x6c, 0x65, 0x73,
	0x22, 0xf7, 0x01, 0x0a, 0x17, 0x52, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x53, 0x74,
	0x6f, 0x72, 0x61, 0x67, 0x65, 0x4f, 0x70, 0x74, 0x69, 0x6f, 0x6e, 0x73, 0x12, 0x51, 0x0a, 0x07,
	0x73, 0x74, 0x6f, 0x72, 0x61, 0x67, 0x65, 0x18, 0x01, 0x20, 0x01, 0x28, 0x0e, 0x32, 0x37, 0x2e,
	0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f,
	0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69,
	0x6e, 0x67, 0x5f, 0x76, 0x31, 0x2e, 0x52, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x53,
	0x74, 0x6f, 0x72, 0x61, 0x67, 0x65, 0x52, 0x07, 0x73, 0x74, 0x6f, 0x72, 0x61, 0x67, 0x65, 0x12,
	0x1d, 0x0a, 0x0a, 0x61, 0x63, 0x63, 0x65, 0x73, 0x73, 0x5f, 0x6b, 0x65, 0x79, 0x18, 0x02, 0x20,
	0x01, 0x28, 0x09, 0x52, 0x09, 0x61, 0x63, 0x63, 0x65, 0x73, 0x73, 0x4b, 0x65, 0x79, 0x12, 0x1d,
	0x0a, 0x0a, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x5f, 0x6b, 0x65, 0x79, 0x18, 0x03, 0x20, 0x01,
	0x28, 0x09, 0x52, 0x09, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79, 0x12, 0x1f, 0x0a,
	0x0b, 0x62, 0x75, 0x63, 0x6b, 0x65, 0x74, 0x5f, 0x6e, 0x61, 0x6d, 0x65, 0x18, 0x04, 0x20, 0x01,
	0x28, 0x09, 0x52, 0x0a, 0x62, 0x75, 0x63, 0x6b, 0x65, 0x74, 0x4e, 0x61, 0x6d, 0x65, 0x12, 0x16,
	0x0a, 0x06, 0x72, 0x65, 0x67, 0x69, 0x6f, 0x6e, 0x18, 0x05, 0x20, 0x01, 0x28, 0x09, 0x52, 0x06,
	0x72, 0x65, 0x67, 0x69, 0x6f, 0x6e, 0x12, 0x12, 0x0a, 0x04, 0x70, 0x61, 0x74, 0x68, 0x18, 0x06,
	0x20, 0x01, 0x28, 0x09, 0x52, 0x04, 0x70, 0x61, 0x74, 0x68, 0x2a, 0x38, 0x0a, 0x10, 0x52, 0x65,
	0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x67, 0x53, 0x74, 0x6f, 0x72, 0x61, 0x67, 0x65, 0x12, 0x24,
	0x0a, 0x20, 0x52, 0x45, 0x43, 0x4f, 0x52, 0x44, 0x49, 0x4e, 0x47, 0x5f, 0x53, 0x54, 0x4f, 0x52,
	0x41, 0x47, 0x45, 0x5f, 0x53, 0x33, 0x5f, 0x55, 0x4e, 0x53, 0x50, 0x45, 0x43, 0x49, 0x46, 0x49,
	0x45, 0x44, 0x10, 0x00, 0x42, 0x0e, 0x5a, 0x0c, 0x72, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x69, 0x6e,
	0x67, 0x5f, 0x76, 0x31, 0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_video_coordinator_recording_v1_recording_proto_rawDescOnce sync.Once
	file_video_coordinator_recording_v1_recording_proto_rawDescData = file_video_coordinator_recording_v1_recording_proto_rawDesc
)

func file_video_coordinator_recording_v1_recording_proto_rawDescGZIP() []byte {
	file_video_coordinator_recording_v1_recording_proto_rawDescOnce.Do(func() {
		file_video_coordinator_recording_v1_recording_proto_rawDescData = protoimpl.X.CompressGZIP(file_video_coordinator_recording_v1_recording_proto_rawDescData)
	})
	return file_video_coordinator_recording_v1_recording_proto_rawDescData
}

var file_video_coordinator_recording_v1_recording_proto_enumTypes = make([]protoimpl.EnumInfo, 1)
var file_video_coordinator_recording_v1_recording_proto_msgTypes = make([]protoimpl.MessageInfo, 3)
var file_video_coordinator_recording_v1_recording_proto_goTypes = []interface{}{
	(RecordingStorage)(0),           // 0: stream.video.coordinator.recording_v1.RecordingStorage
	(*File)(nil),                    // 1: stream.video.coordinator.recording_v1.File
	(*RecordBroadcast)(nil),         // 2: stream.video.coordinator.recording_v1.RecordBroadcast
	(*RecordingStorageOptions)(nil), // 3: stream.video.coordinator.recording_v1.RecordingStorageOptions
}
var file_video_coordinator_recording_v1_recording_proto_depIdxs = []int32{
	1, // 0: stream.video.coordinator.recording_v1.RecordBroadcast.files:type_name -> stream.video.coordinator.recording_v1.File
	0, // 1: stream.video.coordinator.recording_v1.RecordingStorageOptions.storage:type_name -> stream.video.coordinator.recording_v1.RecordingStorage
	2, // [2:2] is the sub-list for method output_type
	2, // [2:2] is the sub-list for method input_type
	2, // [2:2] is the sub-list for extension type_name
	2, // [2:2] is the sub-list for extension extendee
	0, // [0:2] is the sub-list for field type_name
}

func init() { file_video_coordinator_recording_v1_recording_proto_init() }
func file_video_coordinator_recording_v1_recording_proto_init() {
	if File_video_coordinator_recording_v1_recording_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_video_coordinator_recording_v1_recording_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*File); i {
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
		file_video_coordinator_recording_v1_recording_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RecordBroadcast); i {
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
		file_video_coordinator_recording_v1_recording_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*RecordingStorageOptions); i {
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
			RawDescriptor: file_video_coordinator_recording_v1_recording_proto_rawDesc,
			NumEnums:      1,
			NumMessages:   3,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_video_coordinator_recording_v1_recording_proto_goTypes,
		DependencyIndexes: file_video_coordinator_recording_v1_recording_proto_depIdxs,
		EnumInfos:         file_video_coordinator_recording_v1_recording_proto_enumTypes,
		MessageInfos:      file_video_coordinator_recording_v1_recording_proto_msgTypes,
	}.Build()
	File_video_coordinator_recording_v1_recording_proto = out.File
	file_video_coordinator_recording_v1_recording_proto_rawDesc = nil
	file_video_coordinator_recording_v1_recording_proto_goTypes = nil
	file_video_coordinator_recording_v1_recording_proto_depIdxs = nil
}
