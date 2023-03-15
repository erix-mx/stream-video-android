// Code generated by protoc-gen-validate. DO NOT EDIT.
// source: video/coordinator/edge_v1/edge.proto

package edge_v1

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

// Validate checks the field values on Credentials with the rules defined in
// the proto definition for this message. If any rules are violated, the first
// error encountered is returned, or nil if there are no violations.
func (m *Credentials) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on Credentials with the rules defined in
// the proto definition for this message. If any rules are violated, the
// result is a list of violation errors wrapped in CredentialsMultiError, or
// nil if none found.
func (m *Credentials) ValidateAll() error {
	return m.validate(true)
}

func (m *Credentials) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	if m.GetServer() == nil {
		err := CredentialsValidationError{
			field:  "Server",
			reason: "value is required",
		}
		if !all {
			return err
		}
		errors = append(errors, err)
	}

	if all {
		switch v := interface{}(m.GetServer()).(type) {
		case interface{ ValidateAll() error }:
			if err := v.ValidateAll(); err != nil {
				errors = append(errors, CredentialsValidationError{
					field:  "Server",
					reason: "embedded message failed validation",
					cause:  err,
				})
			}
		case interface{ Validate() error }:
			if err := v.Validate(); err != nil {
				errors = append(errors, CredentialsValidationError{
					field:  "Server",
					reason: "embedded message failed validation",
					cause:  err,
				})
			}
		}
	} else if v, ok := interface{}(m.GetServer()).(interface{ Validate() error }); ok {
		if err := v.Validate(); err != nil {
			return CredentialsValidationError{
				field:  "Server",
				reason: "embedded message failed validation",
				cause:  err,
			}
		}
	}

	if utf8.RuneCountInString(m.GetToken()) < 1 {
		err := CredentialsValidationError{
			field:  "Token",
			reason: "value length must be at least 1 runes",
		}
		if !all {
			return err
		}
		errors = append(errors, err)
	}

	for idx, item := range m.GetIceServers() {
		_, _ = idx, item

		if all {
			switch v := interface{}(item).(type) {
			case interface{ ValidateAll() error }:
				if err := v.ValidateAll(); err != nil {
					errors = append(errors, CredentialsValidationError{
						field:  fmt.Sprintf("IceServers[%v]", idx),
						reason: "embedded message failed validation",
						cause:  err,
					})
				}
			case interface{ Validate() error }:
				if err := v.Validate(); err != nil {
					errors = append(errors, CredentialsValidationError{
						field:  fmt.Sprintf("IceServers[%v]", idx),
						reason: "embedded message failed validation",
						cause:  err,
					})
				}
			}
		} else if v, ok := interface{}(item).(interface{ Validate() error }); ok {
			if err := v.Validate(); err != nil {
				return CredentialsValidationError{
					field:  fmt.Sprintf("IceServers[%v]", idx),
					reason: "embedded message failed validation",
					cause:  err,
				}
			}
		}

	}

	if len(errors) > 0 {
		return CredentialsMultiError(errors)
	}

	return nil
}

// CredentialsMultiError is an error wrapping multiple validation errors
// returned by Credentials.ValidateAll() if the designated constraints aren't met.
type CredentialsMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m CredentialsMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m CredentialsMultiError) AllErrors() []error { return m }

// CredentialsValidationError is the validation error returned by
// Credentials.Validate if the designated constraints aren't met.
type CredentialsValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e CredentialsValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e CredentialsValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e CredentialsValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e CredentialsValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e CredentialsValidationError) ErrorName() string { return "CredentialsValidationError" }

// Error satisfies the builtin error interface
func (e CredentialsValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sCredentials.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = CredentialsValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = CredentialsValidationError{}

// Validate checks the field values on LatencyMeasurements with the rules
// defined in the proto definition for this message. If any rules are
// violated, the first error encountered is returned, or nil if there are no violations.
func (m *LatencyMeasurements) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on LatencyMeasurements with the rules
// defined in the proto definition for this message. If any rules are
// violated, the result is a list of violation errors wrapped in
// LatencyMeasurementsMultiError, or nil if none found.
func (m *LatencyMeasurements) ValidateAll() error {
	return m.validate(true)
}

func (m *LatencyMeasurements) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	{
		sorted_keys := make([]string, len(m.GetMeasurements()))
		i := 0
		for key := range m.GetMeasurements() {
			sorted_keys[i] = key
			i++
		}
		sort.Slice(sorted_keys, func(i, j int) bool { return sorted_keys[i] < sorted_keys[j] })
		for _, key := range sorted_keys {
			val := m.GetMeasurements()[key]
			_ = val

			// no validation rules for Measurements[key]

			if all {
				switch v := interface{}(val).(type) {
				case interface{ ValidateAll() error }:
					if err := v.ValidateAll(); err != nil {
						errors = append(errors, LatencyMeasurementsValidationError{
							field:  fmt.Sprintf("Measurements[%v]", key),
							reason: "embedded message failed validation",
							cause:  err,
						})
					}
				case interface{ Validate() error }:
					if err := v.Validate(); err != nil {
						errors = append(errors, LatencyMeasurementsValidationError{
							field:  fmt.Sprintf("Measurements[%v]", key),
							reason: "embedded message failed validation",
							cause:  err,
						})
					}
				}
			} else if v, ok := interface{}(val).(interface{ Validate() error }); ok {
				if err := v.Validate(); err != nil {
					return LatencyMeasurementsValidationError{
						field:  fmt.Sprintf("Measurements[%v]", key),
						reason: "embedded message failed validation",
						cause:  err,
					}
				}
			}

		}
	}

	if len(errors) > 0 {
		return LatencyMeasurementsMultiError(errors)
	}

	return nil
}

// LatencyMeasurementsMultiError is an error wrapping multiple validation
// errors returned by LatencyMeasurements.ValidateAll() if the designated
// constraints aren't met.
type LatencyMeasurementsMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m LatencyMeasurementsMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m LatencyMeasurementsMultiError) AllErrors() []error { return m }

// LatencyMeasurementsValidationError is the validation error returned by
// LatencyMeasurements.Validate if the designated constraints aren't met.
type LatencyMeasurementsValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e LatencyMeasurementsValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e LatencyMeasurementsValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e LatencyMeasurementsValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e LatencyMeasurementsValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e LatencyMeasurementsValidationError) ErrorName() string {
	return "LatencyMeasurementsValidationError"
}

// Error satisfies the builtin error interface
func (e LatencyMeasurementsValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sLatencyMeasurements.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = LatencyMeasurementsValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = LatencyMeasurementsValidationError{}

// Validate checks the field values on Latency with the rules defined in the
// proto definition for this message. If any rules are violated, the first
// error encountered is returned, or nil if there are no violations.
func (m *Latency) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on Latency with the rules defined in the
// proto definition for this message. If any rules are violated, the result is
// a list of violation errors wrapped in LatencyMultiError, or nil if none found.
func (m *Latency) ValidateAll() error {
	return m.validate(true)
}

func (m *Latency) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	if len(errors) > 0 {
		return LatencyMultiError(errors)
	}

	return nil
}

// LatencyMultiError is an error wrapping multiple validation errors returned
// by Latency.ValidateAll() if the designated constraints aren't met.
type LatencyMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m LatencyMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m LatencyMultiError) AllErrors() []error { return m }

// LatencyValidationError is the validation error returned by Latency.Validate
// if the designated constraints aren't met.
type LatencyValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e LatencyValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e LatencyValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e LatencyValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e LatencyValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e LatencyValidationError) ErrorName() string { return "LatencyValidationError" }

// Error satisfies the builtin error interface
func (e LatencyValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sLatency.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = LatencyValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = LatencyValidationError{}

// Validate checks the field values on Coordinates with the rules defined in
// the proto definition for this message. If any rules are violated, the first
// error encountered is returned, or nil if there are no violations.
func (m *Coordinates) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on Coordinates with the rules defined in
// the proto definition for this message. If any rules are violated, the
// result is a list of violation errors wrapped in CoordinatesMultiError, or
// nil if none found.
func (m *Coordinates) ValidateAll() error {
	return m.validate(true)
}

func (m *Coordinates) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for Lat

	// no validation rules for Long

	if len(errors) > 0 {
		return CoordinatesMultiError(errors)
	}

	return nil
}

// CoordinatesMultiError is an error wrapping multiple validation errors
// returned by Coordinates.ValidateAll() if the designated constraints aren't met.
type CoordinatesMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m CoordinatesMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m CoordinatesMultiError) AllErrors() []error { return m }

// CoordinatesValidationError is the validation error returned by
// Coordinates.Validate if the designated constraints aren't met.
type CoordinatesValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e CoordinatesValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e CoordinatesValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e CoordinatesValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e CoordinatesValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e CoordinatesValidationError) ErrorName() string { return "CoordinatesValidationError" }

// Error satisfies the builtin error interface
func (e CoordinatesValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sCoordinates.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = CoordinatesValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = CoordinatesValidationError{}

// Validate checks the field values on Edge with the rules defined in the proto
// definition for this message. If any rules are violated, the first error
// encountered is returned, or nil if there are no violations.
func (m *Edge) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on Edge with the rules defined in the
// proto definition for this message. If any rules are violated, the result is
// a list of violation errors wrapped in EdgeMultiError, or nil if none found.
func (m *Edge) ValidateAll() error {
	return m.validate(true)
}

func (m *Edge) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	if utf8.RuneCountInString(m.GetName()) < 1 {
		err := EdgeValidationError{
			field:  "Name",
			reason: "value length must be at least 1 runes",
		}
		if !all {
			return err
		}
		errors = append(errors, err)
	}

	if utf8.RuneCountInString(m.GetLatencyUrl()) < 1 {
		err := EdgeValidationError{
			field:  "LatencyUrl",
			reason: "value length must be at least 1 runes",
		}
		if !all {
			return err
		}
		errors = append(errors, err)
	}

	if all {
		switch v := interface{}(m.GetCoordinates()).(type) {
		case interface{ ValidateAll() error }:
			if err := v.ValidateAll(); err != nil {
				errors = append(errors, EdgeValidationError{
					field:  "Coordinates",
					reason: "embedded message failed validation",
					cause:  err,
				})
			}
		case interface{ Validate() error }:
			if err := v.Validate(); err != nil {
				errors = append(errors, EdgeValidationError{
					field:  "Coordinates",
					reason: "embedded message failed validation",
					cause:  err,
				})
			}
		}
	} else if v, ok := interface{}(m.GetCoordinates()).(interface{ Validate() error }); ok {
		if err := v.Validate(); err != nil {
			return EdgeValidationError{
				field:  "Coordinates",
				reason: "embedded message failed validation",
				cause:  err,
			}
		}
	}

	if len(errors) > 0 {
		return EdgeMultiError(errors)
	}

	return nil
}

// EdgeMultiError is an error wrapping multiple validation errors returned by
// Edge.ValidateAll() if the designated constraints aren't met.
type EdgeMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m EdgeMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m EdgeMultiError) AllErrors() []error { return m }

// EdgeValidationError is the validation error returned by Edge.Validate if the
// designated constraints aren't met.
type EdgeValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e EdgeValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e EdgeValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e EdgeValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e EdgeValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e EdgeValidationError) ErrorName() string { return "EdgeValidationError" }

// Error satisfies the builtin error interface
func (e EdgeValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sEdge.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = EdgeValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = EdgeValidationError{}

// Validate checks the field values on ICEServer with the rules defined in the
// proto definition for this message. If any rules are violated, the first
// error encountered is returned, or nil if there are no violations.
func (m *ICEServer) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on ICEServer with the rules defined in
// the proto definition for this message. If any rules are violated, the
// result is a list of violation errors wrapped in ICEServerMultiError, or nil
// if none found.
func (m *ICEServer) ValidateAll() error {
	return m.validate(true)
}

func (m *ICEServer) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for Username

	// no validation rules for Password

	if len(errors) > 0 {
		return ICEServerMultiError(errors)
	}

	return nil
}

// ICEServerMultiError is an error wrapping multiple validation errors returned
// by ICEServer.ValidateAll() if the designated constraints aren't met.
type ICEServerMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m ICEServerMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m ICEServerMultiError) AllErrors() []error { return m }

// ICEServerValidationError is the validation error returned by
// ICEServer.Validate if the designated constraints aren't met.
type ICEServerValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e ICEServerValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e ICEServerValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e ICEServerValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e ICEServerValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e ICEServerValidationError) ErrorName() string { return "ICEServerValidationError" }

// Error satisfies the builtin error interface
func (e ICEServerValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sICEServer.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = ICEServerValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = ICEServerValidationError{}

// Validate checks the field values on EdgeServer with the rules defined in the
// proto definition for this message. If any rules are violated, the first
// error encountered is returned, or nil if there are no violations.
func (m *EdgeServer) Validate() error {
	return m.validate(false)
}

// ValidateAll checks the field values on EdgeServer with the rules defined in
// the proto definition for this message. If any rules are violated, the
// result is a list of violation errors wrapped in EdgeServerMultiError, or
// nil if none found.
func (m *EdgeServer) ValidateAll() error {
	return m.validate(true)
}

func (m *EdgeServer) validate(all bool) error {
	if m == nil {
		return nil
	}

	var errors []error

	// no validation rules for Url

	// no validation rules for EdgeName

	if len(errors) > 0 {
		return EdgeServerMultiError(errors)
	}

	return nil
}

// EdgeServerMultiError is an error wrapping multiple validation errors
// returned by EdgeServer.ValidateAll() if the designated constraints aren't met.
type EdgeServerMultiError []error

// Error returns a concatenation of all the error messages it wraps.
func (m EdgeServerMultiError) Error() string {
	var msgs []string
	for _, err := range m {
		msgs = append(msgs, err.Error())
	}
	return strings.Join(msgs, "; ")
}

// AllErrors returns a list of validation violation errors.
func (m EdgeServerMultiError) AllErrors() []error { return m }

// EdgeServerValidationError is the validation error returned by
// EdgeServer.Validate if the designated constraints aren't met.
type EdgeServerValidationError struct {
	field  string
	reason string
	cause  error
	key    bool
}

// Field function returns field value.
func (e EdgeServerValidationError) Field() string { return e.field }

// Reason function returns reason value.
func (e EdgeServerValidationError) Reason() string { return e.reason }

// Cause function returns cause value.
func (e EdgeServerValidationError) Cause() error { return e.cause }

// Key function returns key value.
func (e EdgeServerValidationError) Key() bool { return e.key }

// ErrorName returns error name.
func (e EdgeServerValidationError) ErrorName() string { return "EdgeServerValidationError" }

// Error satisfies the builtin error interface
func (e EdgeServerValidationError) Error() string {
	cause := ""
	if e.cause != nil {
		cause = fmt.Sprintf(" | caused by: %v", e.cause)
	}

	key := ""
	if e.key {
		key = "key for "
	}

	return fmt.Sprintf(
		"invalid %sEdgeServer.%s: %s%s",
		key,
		e.field,
		e.reason,
		cause)
}

var _ error = EdgeServerValidationError{}

var _ interface {
	Field() string
	Reason() string
	Key() bool
	Cause() error
	ErrorName() string
} = EdgeServerValidationError{}