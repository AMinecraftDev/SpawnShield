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
package net.techcable.spawnshield;

import lombok.RequiredArgsConstructor;
import net.techcable.techutils.yamler.InternalConverter;
import net.techcable.techutils.yamler.converter.Converter;

import java.lang.reflect.ParameterizedType;

public enum BlockMode {
    TELEPORT,
    KNOCKBACK,
    FORCEFIELD;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static class BlockModeConverter implements Converter {
        public BlockModeConverter(InternalConverter internalConverter) {
            //I don't know what to do with an internal converter
        }

        @Override
        public Object toConfig(Class<?> aClass, Object o, ParameterizedType parameterizedType) throws Exception {
            BlockMode mode = (BlockMode) o;
            return o.toString();
        }

        @Override
        public Object fromConfig(Class<?> aClass, Object o, ParameterizedType parameterizedType) throws Exception {
            String name = o.toString();
            try {
                return BlockMode.valueOf(name.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(name + " is not a valid mode for SpawnShield");
            }
        }

        @Override
        public boolean supports(Class<?> aClass) {
            return BlockMode.class.isAssignableFrom(aClass);
        }
    }
}
