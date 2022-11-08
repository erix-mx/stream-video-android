// Code generated by protoc-gen-validate. DO NOT EDIT.
// source: video/coordinator/recording_v1/recording.proto

package recording_v1

import (
	"bytes"
	"errors"
	"fmt"
	"net"
	"net/mail"
	"net/url"
	"regexp"
	"sort"
	"strings"
	"time"
	"unicode/utf8"

	"google.golang.org/protobuf/types/known/anypb"
)

// ensure the imports are used
var (
	_ = bytes.MinRead
	_ = errors.New("")
	_ = fmt.Print
	_ = utf8.UTFMax
	_ = (*regexp.Regexp)(nil)
	_ = (*strings.Reader)(nil)
	_ = net.IPv4len
	_ = time.Duration(0)
	_ = (*url.URL)(nil)
	_ = (*mail.Address)(nil)
	_ = anypb.Any{}
	_ = sort.Sort
)

// Validate checks the field values on Recording with the rules defined in the
// proto definition for this message. If any rules are violated, the first
// error encountered is returned, or nil if there are no violations.
func (m *Recording) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on Recording with the rules defined in
// the proto definition for this message. If any rules are violated, the
// result is a list of violation errors wrapped in RecordingMultiError, or nil
// if none found.
func (m *Recording) ValidateAll() error {
	return m.validate(true)
}

func (m *Recording) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for CallType

	// no validation rules for CallId

	// no validation rules for S3FileUrl

	// no validation rules for StartTime

	// no validation rules for StopTime

	if len(errors) > 0 {
		return RecordingMultiError(errors)
	}

	return nil
}

// RecordingMultiError is an error wrapping multiple validation errors returned
// by Recording.ValidateAll() if the designated constraints aren't met.
type RecordingMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m RecordingMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m RecordingMultiError) AllErrors() []error { return m }

// RecordingValidationError is the validation error returned by
// Recording.Validate if the designated constraints aren't met.
type RecordingValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e RecordingValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e RecordingValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e RecordingValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e RecordingValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e RecordingValidationError) ErrorName() string { return "RecordingValidationError" }

// Error satisfies the builtin error interface
func (e RecordingValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sRecording.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = RecordingValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = RecordingValidationError{}

// Validate checks the field values on File with the rules defined in the proto
// definition for this message. If any rules are violated, the first error
// encountered is returned, or nil if there are no violations.
func (m *File) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on File with the rules defined in the
// proto definition for this message. If any rules are violated, the result is
// a list of violation errors wrapped in FileMultiError, or nil if none found.
func (m *File) ValidateAll() error {
	return m.validate(true)
}

func (m *File) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for Type

	// no validation rules for Composite

	// no validation rules for UserId

	// no validation rules for Url

	if len(errors) > 0 {
		return FileMultiError(errors)
	}

	return nil
}

// FileMultiError is an error wrapping multiple validation errors returned by
// File.ValidateAll() if the designated constraints aren't met.
type FileMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m FileMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m FileMultiError) AllErrors() []error { return m }

// FileValidationError is the validation error returned by File.Validate if the
// designated constraints aren't met.
type FileValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e FileValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e FileValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e FileValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e FileValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e FileValidationError) ErrorName() string { return "FileValidationError" }

// Error satisfies the builtin error interface
func (e FileValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sFile.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = FileValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = FileValidationError{}

// Validate checks the field values on RecordBroadcast with the rules defined
// in the proto definition for this message. If any rules are violated, the
// first error encountered is returned, or nil if there are no violations.
func (m *RecordBroadcast) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on RecordBroadcast with the rules
// defined in the proto definition for this message. If any rules are
// violated, the result is a list of violation errors wrapped in
// RecordBroadcastMultiError, or nil if none found.
func (m *RecordBroadcast) ValidateAll() error {
	return m.validate(true)
}

func (m *RecordBroadcast) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for Composite

	for idx, item := range m.GetFiles() {
		_, _ = idx, item

		if all {
			switch v := interface{}(item).(type) {
			case interface{ ValidateAll() error }:
				if err := v.ValidateAll(); err != nil {
					errors = append(errors, RecordBroadcastValidationError{
						field:  fmt.Sprintf("Files[%v]", idx),
						reason: "embedded message failed validation",
						cause:  err,
					})
				}
			case interface{ Validate() error }:
				if err := v.Validate(); err != nil {
					errors = append(errors, RecordBroadcastValidationError{
						field:  fmt.Sprintf("Files[%v]", idx),
						reason: "embedded message failed validation",
						cause:  err,
					})
				}
			}
		} else if v, ok := interface{}(item).(interface{ Validate() error }); ok {
			if err := v.Validate(); err != nil {
				return RecordBroadcastValidationError{
					field:  fmt.Sprintf("Files[%v]", idx),
					reason: "embedded message failed validation",
					cause:  err,
				}
			}
		}

	}

	if len(errors) > 0 {
		return RecordBroadcastMultiError(errors)
	}

	return nil
}

// RecordBroadcastMultiError is an error wrapping multiple validation errors
// returned by RecordBroadcast.ValidateAll() if the designated constraints
// aren't met.
type RecordBroadcastMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m RecordBroadcastMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m RecordBroadcastMultiError) AllErrors() []error { return m }

// RecordBroadcastValidationError is the validation error returned by
// RecordBroadcast.Validate if the designated constraints aren't met.
type RecordBroadcastValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e RecordBroadcastValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e RecordBroadcastValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e RecordBroadcastValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e RecordBroadcastValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e RecordBroadcastValidationError) ErrorName() string { return "RecordBroadcastValidationError" }

// Error satisfies the builtin error interface
func (e RecordBroadcastValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sRecordBroadcast.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = RecordBroadcastValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = RecordBroadcastValidationError{}

// Validate checks the field values on RecordingStorageOptions with the rules
// defined in the proto definition for this message. If any rules are
// violated, the first error encountered is returned, or nil if there are no violations.
func (m *RecordingStorageOptions) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on RecordingStorageOptions with the
// rules defined in the proto definition for this message. If any rules are
// violated, the result is a list of violation errors wrapped in
// RecordingStorageOptionsMultiError, or nil if none found.
func (m *RecordingStorageOptions) ValidateAll() error {
	return m.validate(true)
}

func (m *RecordingStorageOptions) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for Storage

	// no validation rules for AccessKey

	// no validation rules for SecretKey

	// no validation rules for BucketName

	// no validation rules for Region

	// no validation rules for Path

	if len(errors) > 0 {
		return RecordingStorageOptionsMultiError(errors)
	}

	return nil
}

// RecordingStorageOptionsMultiError is an error wrapping multiple validation
// errors returned by RecordingStorageOptions.ValidateAll() if the designated
// constraints aren't met.
type RecordingStorageOptionsMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m RecordingStorageOptionsMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m RecordingStorageOptionsMultiError) AllErrors() []error { return m }

// RecordingStorageOptionsValidationError is the validation error returned by
// RecordingStorageOptions.Validate if the designated constraints aren't met.
type RecordingStorageOptionsValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e RecordingStorageOptionsValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e RecordingStorageOptionsValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e RecordingStorageOptionsValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e RecordingStorageOptionsValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e RecordingStorageOptionsValidationError) ErrorName() string {
	return "RecordingStorageOptionsValidationError"
}

// Error satisfies the builtin error interface
func (e RecordingStorageOptionsValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sRecordingStorageOptions.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = RecordingStorageOptionsValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = RecordingStorageOptionsValidationError{}