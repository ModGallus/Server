package org.areillan.game.node.entity.player.ai.random;

import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.item.Item;
import org.areillan.tools.RandomFunction;

public enum RandomAITask {
	ROAMING(5, false, new Item[] {}),
	WOODCUTTING(10, false, new Item[] {new Item(1351)}),
	MINING(10 ,false, new Item[] {new Item(1265)}),
	NOOB_EMOTE(5, false, new Item[] {}),
	NOOB_FOLLOW(5, false, new Item[] {}),
	NOOB_BEG(5, false, new Item[] {}),
	FISHING(10, false, new Item[] {}),
	AFKING(2, false, new Item[] {});
	
	int swapRate;
	boolean bankIfFull;
	Item[] requiredItems;
	Node target;
	
	RandomAITask(int swapRate, boolean bankIfFull, Item[] requiredItems) {
		this.swapRate = swapRate;
		this.bankIfFull = bankIfFull;
		this.requiredItems = requiredItems;
	}
	
	boolean shouldSwap(AIPlayer bot) {
		if (bankIfFull && bot.getInventory().isFull() ||
			!bot.getInventory().containsItems(requiredItems)) {
			return true;
		}
		return RandomFunction.random(1, swapRate) == 1;
	}
	
	// Target can be an object, player, item, etc.
	void setTarget(Node target) {
		this.target = target;
	}
	
	Node getTarget() {
		return target;
	}
	
	public static final RandomEnum<RandomAITask> AITask =
	        new RandomEnum<RandomAITask>(RandomAITask.class);
	
	static class RandomEnum<E extends Enum<?>> {
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RandomFunction.getRandom(values.length - 1)];
        }
    }
}
