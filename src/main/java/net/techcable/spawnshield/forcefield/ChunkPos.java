/**
 * Copyright (c) 2015 Nicholas Schlabach
 *
 * If there is conflict between two versions of this license, [the version found on gtihub](https://gist.github.com/Techcable/ea146b481870e3736b48) will take precedence.
 *
 * Permission is granted to operate this program provided that:
 * - You have purchased this plugin from Nicholas Schlabach (Techcable)
 * - Techcable has given you his express permission to operate/run this program
 * - Techcable has released this software publicly on either dev.bukkit.org, spigotmc.org or another software hosting site
 * - You do not modify this software in any way
 * - You do not redistrubute this software to anyone else
 * - You do not attempt to deobfuscate, decompile, or reverese engneer this plugin in any way
 * - Nicholas Schlabach (Techcable) reserves the right to change these terms an conditions at any time, with or without warning
 *
 * If any of the above terms are violated this plugin is no longer valid and you must stop using this software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.techcable.spawnshield.forcefield;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * An immutable position of a chunk
 */
@RequiredArgsConstructor
@Getter
public class ChunkPos {
    private final int x, z;
    private final World world;

    public int getAbsoluteX(int relativeX) {
        return fromRelative(getX(), relativeX);
    }
    public int getAbsoluteZ(int absoluteZ) {
        return fromRelative(getZ(), absoluteZ);
    }
    public static ChunkPos fromChunk(Chunk chunk) {
        return new ChunkPos(chunk.getX(), chunk.getZ(), chunk.getWorld());
    }
    public static ChunkPos fromLocation(Location l) {
        return new ChunkPos(l.getBlockX() >> 4, l.getBlockZ() >> 4, l.getWorld());
    }
    public static int toRelative(int absolute) {
        return absolute & 0xF; //First 16 bits
    }
    public static int fromRelative(int chunk, int relative) {
        return (chunk << 4) | (relative & 0xF);
    }

    public boolean isLoaded() {
        return getWorld().isChunkLoaded(getX(), getZ());
    }
}
