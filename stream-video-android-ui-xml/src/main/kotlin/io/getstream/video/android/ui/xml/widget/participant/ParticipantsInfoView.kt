package io.getstream.video.android.ui.xml.widget.participant

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Space
import androidx.constraintlayout.widget.ConstraintLayout
import io.getstream.video.android.common.util.buildLargeCallText
import io.getstream.video.android.common.util.buildSmallCallText
import io.getstream.video.android.model.CallStatus
import io.getstream.video.android.model.CallUser
import io.getstream.video.android.ui.common.R
import io.getstream.video.android.ui.xml.databinding.ViewParticipantsInfoBinding
import io.getstream.video.android.ui.xml.utils.extensions.inflater
import io.getstream.video.android.ui.xml.utils.extensions.dpToPx
import io.getstream.video.android.ui.xml.widget.avatar.AvatarView

internal class ParticipantsInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewParticipantsInfoBinding.inflate(inflater, this)

    public fun setCallStatus(callStatus: CallStatus) {
        binding.statusText.text = when (callStatus) {
            CallStatus.Incoming -> context.getString(R.string.call_status_incoming)
            CallStatus.Outgoing -> context.getString(R.string.call_status_outgoing)
            is CallStatus.Calling -> callStatus.duration
        }
    }

    public fun setParticipants(participants: List<CallUser>) {
        setParticipantsAvatars(participants)
        setParticipantsText(participants)
    }

    private fun setParticipantsAvatars(participants: List<CallUser>) {
        binding.avatarsHolder.removeAllViews()

        participants.take(2).forEachIndexed { index, participant ->
            if (index > 0) {
                addAvatarSpacer()
            }

            addAvatar(participant.imageUrl, participant.name)
        }

        if (participants.size == 3) {
            val participant = participants[2]
            addAvatarSpacer()
            addAvatar(participant.imageUrl, participant.name)
        }

        if (participants.size > 3) {
            addAvatarSpacer()
            addAvatar("", "+${participants.size - 2}")
        }
    }

    private fun setParticipantsText(participants: List<CallUser>) {
        binding.participantsText.text = if (participants.size < 3) {
            buildSmallCallText(participants)
        } else {
            buildLargeCallText(participants)
        }
    }

    private fun addAvatar(imageUrl: String?, name: String) {
        val avatar = AvatarView(context).apply {
            layoutParams = LayoutParams(80.dpToPx(), LayoutParams.MATCH_PARENT)
        }
        avatar.setData(imageUrl ?: "", name)
        binding.avatarsHolder.addView(avatar)
    }

    private fun addAvatarSpacer() {
        binding.avatarsHolder.addView(View(context).apply {
            layoutParams = LayoutParams(20.dpToPx(), LayoutParams.MATCH_PARENT)
        })
    }
}