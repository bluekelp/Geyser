/*
 * Copyright (c) 2019 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.connector.network.session.cache;

import com.github.steveice10.packetlib.packet.Packet;
import lombok.Getter;
import lombok.Setter;
import org.geysermc.connector.inventory.Inventory;
import org.geysermc.connector.network.session.GeyserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryCache {

    private GeyserSession session;

    @Getter
    @Setter
    private Inventory openInventory;

    @Getter
    private Map<Integer, Inventory> inventories = new HashMap<Integer, Inventory>();

    @Getter
    private Map<Integer, List<Packet>> cachedPackets = new HashMap<Integer, List<Packet>>();

    public InventoryCache(GeyserSession session) {
        this.session = session;

        // This is the player's inventory
        inventories.put(0, new Inventory(0, null, 45));
    }

    public Inventory getPlayerInventory() {
        return inventories.get(0);
    }

    public void cacheInventory(Inventory inventory) {
        inventories.put(inventory.getId(), inventory);
    }

    public void uncacheInventory(int id) {
        inventories.remove(id);
    }

    public void cachePacket(int id, Packet packet) {
        List<Packet> packets = cachedPackets.getOrDefault(id, new ArrayList<Packet>());
        packets.add(packet);
        cachedPackets.put(id, packets);
    }
}
