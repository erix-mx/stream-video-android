# Copyright (c) 2014-2023 Stream.io Inc. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

awk '/getstream/ && !/compose/ && /core/ && !/android-xml/ && !/app/ && !/ui-common/' app/src/main/baseline-prof.txt > stream-video-android-core/src/main/baseline-prof.txt
awk '/getstream/ && /compose/ && !/core/ && !/android-xml/ && !/app/ && !/ui-common/' app/src/main/baseline-prof.txt > stream-video-android-compose/src/main/baseline-prof.txt
awk '/getstream/ && !/compose/ && !/core/ && /android-xml/ && !/app/ && !/ui-common/' app/src/main/baseline-prof.txt > stream-video-android-xml/src/main/baseline-prof.txt
awk '/getstream/ && !/compose/ && !/core/ && !/android-xml/ && !/app/ && /ui-common/' app/src/main/baseline-prof.txt > stream-video-android-ui-common/src/main/baseline-prof.txt