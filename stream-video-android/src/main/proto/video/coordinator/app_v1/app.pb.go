// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.28.0
// 	protoc        v3.21.5
// source: video/coordinator/app_v1/app.proto

package app_v1

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

type App struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Id      int64            `protobuf:"varint,1,opt,name=id,proto3" json:"id,omitempty"`
	Webhook *WebhookSettings `protobuf:"bytes,2,opt,name=webhook,proto3" json:"webhook,omitempty"`
}

func (x *App) Reset() {
	*x = App{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_app_v1_app_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *App) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*App) ProtoMessage() {}

func (x *App) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_app_v1_app_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use App.ProtoReflect.Descriptor instead.
func (*App) Descriptor() ([]byte, []int) {
	return file_video_coordinator_app_v1_app_proto_rawDescGZIP(), []int{0}
}

func (x *App) GetId() int64 {
	if x != nil {
		return x.Id
	}
	return 0
}

func (x *App) GetWebhook() *WebhookSettings {
	if x != nil {
		return x.Webhook
	}
	return nil
}

// Keep in sync with stream.config.webhook.AppSettings
type WebhookSettings struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Http *HttpWebhook `protobuf:"bytes,1,opt,name=http,proto3" json:"http,omitempty"`
	Sqs  *SqsWebhook  `protobuf:"bytes,2,opt,name=sqs,proto3" json:"sqs,omitempty"`
}

func (x *WebhookSettings) Reset() {
	*x = WebhookSettings{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_app_v1_app_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *WebhookSettings) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*WebhookSettings) ProtoMessage() {}

func (x *WebhookSettings) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_app_v1_app_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use WebhookSettings.ProtoReflect.Descriptor instead.
func (*WebhookSettings) Descriptor() ([]byte, []int) {
	return file_video_coordinator_app_v1_app_proto_rawDescGZIP(), []int{1}
}

func (x *WebhookSettings) GetHttp() *HttpWebhook {
	if x != nil {
		return x.Http
	}
	return nil
}

func (x *WebhookSettings) GetSqs() *SqsWebhook {
	if x != nil {
		return x.Sqs
	}
	return nil
}

type HttpWebhook struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Url string `protobuf:"bytes,1,opt,name=url,proto3" json:"url,omitempty"`
}

func (x *HttpWebhook) Reset() {
	*x = HttpWebhook{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_app_v1_app_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *HttpWebhook) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*HttpWebhook) ProtoMessage() {}

func (x *HttpWebhook) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_app_v1_app_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use HttpWebhook.ProtoReflect.Descriptor instead.
func (*HttpWebhook) Descriptor() ([]byte, []int) {
	return file_video_coordinator_app_v1_app_proto_rawDescGZIP(), []int{2}
}

func (x *HttpWebhook) GetUrl() string {
	if x != nil {
		return x.Url
	}
	return ""
}

type SqsWebhook struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Url    string `protobuf:"bytes,1,opt,name=url,proto3" json:"url,omitempty"`
	Key    string `protobuf:"bytes,2,opt,name=key,proto3" json:"key,omitempty"`
	Secret string `protobuf:"bytes,3,opt,name=secret,proto3" json:"secret,omitempty"`
}

func (x *SqsWebhook) Reset() {
	*x = SqsWebhook{}
	if protoimpl.UnsafeEnabled {
		mi := &file_video_coordinator_app_v1_app_proto_msgTypes[3]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *SqsWebhook) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*SqsWebhook) ProtoMessage() {}

func (x *SqsWebhook) ProtoReflect() protoreflect.Message {
	mi := &file_video_coordinator_app_v1_app_proto_msgTypes[3]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use SqsWebhook.ProtoReflect.Descriptor instead.
func (*SqsWebhook) Descriptor() ([]byte, []int) {
	return file_video_coordinator_app_v1_app_proto_rawDescGZIP(), []int{3}
}

func (x *SqsWebhook) GetUrl() string {
	if x != nil {
		return x.Url
	}
	return ""
}

func (x *SqsWebhook) GetKey() string {
	if x != nil {
		return x.Key
	}
	return ""
}

func (x *SqsWebhook) GetSecret() string {
	if x != nil {
		return x.Secret
	}
	return ""
}

var File_video_coordinator_app_v1_app_proto protoreflect.FileDescriptor

var file_video_coordinator_app_v1_app_proto_rawDesc = []byte{
	0x0a, 0x22, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2f, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61,
	0x74, 0x6f, 0x72, 0x2f, 0x61, 0x70, 0x70, 0x5f, 0x76, 0x31, 0x2f, 0x61, 0x70, 0x70, 0x2e, 0x70,
	0x72, 0x6f, 0x74, 0x6f, 0x12, 0x1f, 0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64,
	0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x61,
	0x70, 0x70, 0x5f, 0x76, 0x31, 0x22, 0x61, 0x0a, 0x03, 0x41, 0x70, 0x70, 0x12, 0x0e, 0x0a, 0x02,
	0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x03, 0x52, 0x02, 0x69, 0x64, 0x12, 0x4a, 0x0a, 0x07,
	0x77, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x18, 0x02, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x30, 0x2e,
	0x73, 0x74, 0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f,
	0x72, 0x64, 0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x61, 0x70, 0x70, 0x5f, 0x76, 0x31, 0x2e,
	0x57, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x53, 0x65, 0x74, 0x74, 0x69, 0x6e, 0x67, 0x73, 0x52,
	0x07, 0x77, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x22, 0x92, 0x01, 0x0a, 0x0f, 0x57, 0x65, 0x62,
	0x68, 0x6f, 0x6f, 0x6b, 0x53, 0x65, 0x74, 0x74, 0x69, 0x6e, 0x67, 0x73, 0x12, 0x40, 0x0a, 0x04,
	0x68, 0x74, 0x74, 0x70, 0x18, 0x01, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x2c, 0x2e, 0x73, 0x74, 0x72,
	0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64, 0x69,
	0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x61, 0x70, 0x70, 0x5f, 0x76, 0x31, 0x2e, 0x48, 0x74, 0x74,
	0x70, 0x57, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x52, 0x04, 0x68, 0x74, 0x74, 0x70, 0x12, 0x3d,
	0x0a, 0x03, 0x73, 0x71, 0x73, 0x18, 0x02, 0x20, 0x01, 0x28, 0x0b, 0x32, 0x2b, 0x2e, 0x73, 0x74,
	0x72, 0x65, 0x61, 0x6d, 0x2e, 0x76, 0x69, 0x64, 0x65, 0x6f, 0x2e, 0x63, 0x6f, 0x6f, 0x72, 0x64,
	0x69, 0x6e, 0x61, 0x74, 0x6f, 0x72, 0x2e, 0x61, 0x70, 0x70, 0x5f, 0x76, 0x31, 0x2e, 0x53, 0x71,
	0x73, 0x57, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x52, 0x03, 0x73, 0x71, 0x73, 0x22, 0x1f, 0x0a,
	0x0b, 0x48, 0x74, 0x74, 0x70, 0x57, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x12, 0x10, 0x0a, 0x03,
	0x75, 0x72, 0x6c, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x03, 0x75, 0x72, 0x6c, 0x22, 0x48,
	0x0a, 0x0a, 0x53, 0x71, 0x73, 0x57, 0x65, 0x62, 0x68, 0x6f, 0x6f, 0x6b, 0x12, 0x10, 0x0a, 0x03,
	0x75, 0x72, 0x6c, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x03, 0x75, 0x72, 0x6c, 0x12, 0x10,
	0x0a, 0x03, 0x6b, 0x65, 0x79, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x03, 0x6b, 0x65, 0x79,
	0x12, 0x16, 0x0a, 0x06, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x18, 0x03, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x06, 0x73, 0x65, 0x63, 0x72, 0x65, 0x74, 0x42, 0x08, 0x5a, 0x06, 0x61, 0x70, 0x70, 0x5f,
	0x76, 0x31, 0x62, 0x06, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x33,
}

var (
	file_video_coordinator_app_v1_app_proto_rawDescOnce sync.Once
	file_video_coordinator_app_v1_app_proto_rawDescData = file_video_coordinator_app_v1_app_proto_rawDesc
)

func file_video_coordinator_app_v1_app_proto_rawDescGZIP() []byte {
	file_video_coordinator_app_v1_app_proto_rawDescOnce.Do(func() {
		file_video_coordinator_app_v1_app_proto_rawDescData = protoimpl.X.CompressGZIP(file_video_coordinator_app_v1_app_proto_rawDescData)
	})
	return file_video_coordinator_app_v1_app_proto_rawDescData
}

var file_video_coordinator_app_v1_app_proto_msgTypes = make([]protoimpl.MessageInfo, 4)
var file_video_coordinator_app_v1_app_proto_goTypes = []interface{}{
	(*App)(nil),             // 0: stream.video.coordinator.app_v1.App
	(*WebhookSettings)(nil), // 1: stream.video.coordinator.app_v1.WebhookSettings
	(*HttpWebhook)(nil),     // 2: stream.video.coordinator.app_v1.HttpWebhook
	(*SqsWebhook)(nil),      // 3: stream.video.coordinator.app_v1.SqsWebhook
}
var file_video_coordinator_app_v1_app_proto_depIdxs = []int32{
	1, // 0: stream.video.coordinator.app_v1.App.webhook:type_name -> stream.video.coordinator.app_v1.WebhookSettings
	2, // 1: stream.video.coordinator.app_v1.WebhookSettings.http:type_name -> stream.video.coordinator.app_v1.HttpWebhook
	3, // 2: stream.video.coordinator.app_v1.WebhookSettings.sqs:type_name -> stream.video.coordinator.app_v1.SqsWebhook
	3, // [3:3] is the sub-list for method output_type
	3, // [3:3] is the sub-list for method input_type
	3, // [3:3] is the sub-list for extension type_name
	3, // [3:3] is the sub-list for extension extendee
	0, // [0:3] is the sub-list for field type_name
}

func init() { file_video_coordinator_app_v1_app_proto_init() }
func file_video_coordinator_app_v1_app_proto_init() {
	if File_video_coordinator_app_v1_app_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_video_coordinator_app_v1_app_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*App); i {
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
		file_video_coordinator_app_v1_app_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*WebhookSettings); i {
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
		file_video_coordinator_app_v1_app_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*HttpWebhook); i {
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
		file_video_coordinator_app_v1_app_proto_msgTypes[3].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*SqsWebhook); i {
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
			RawDescriptor: file_video_coordinator_app_v1_app_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   4,
			NumExtensions: 0,
			NumServices:   0,
		},
		GoTypes:           file_video_coordinator_app_v1_app_proto_goTypes,
		DependencyIndexes: file_video_coordinator_app_v1_app_proto_depIdxs,
		MessageInfos:      file_video_coordinator_app_v1_app_proto_msgTypes,
	}.Build()
	File_video_coordinator_app_v1_app_proto = out.File
	file_video_coordinator_app_v1_app_proto_rawDesc = nil
	file_video_coordinator_app_v1_app_proto_goTypes = nil
	file_video_coordinator_app_v1_app_proto_depIdxs = nil
}
