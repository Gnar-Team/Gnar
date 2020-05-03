package xyz.gnarbot.gnar.commands.music.dj

import me.devoxin.flight.api.Context
import me.devoxin.flight.api.annotations.Command
import xyz.gnarbot.gnar.Launcher
import xyz.gnarbot.gnar.entities.framework.CheckVoiceState
import xyz.gnarbot.gnar.entities.framework.DJ
import xyz.gnarbot.gnar.entities.framework.MusicCog
import xyz.gnarbot.gnar.utils.extensions.manager

class Stop : MusicCog(false, false, false) {
    @DJ
    @CheckVoiceState
    @Command(aliases = ["leave", "end", "st", "fuckoff"], description = "Stop and clear the music player.")
    fun stop(ctx: Context, clear: Boolean = false) {
        val karen = ctx.manager

        if (clear) {
            karen.scheduler.queue.clear()
        }

        karen.discordFMTrack = null
        ctx.guild!!.audioManager.closeAudioConnection()
        Launcher.players.destroy(ctx.guild!!.idLong)

        ctx.send("Playback has been completely stopped. If you want to clear the queue run `${ctx.trigger}clearqueue` or `${ctx.trigger}stop yes`")
    }
}