package org.areillan.game.node.entity.player.ai.random;

import java.util.List;

import org.areillan.game.container.impl.EquipmentContainer;
import org.areillan.game.content.global.consumable.Consumable;
import org.areillan.game.content.global.consumable.ConsumableProperties;
import org.areillan.game.content.global.consumable.Consumables;
import org.areillan.game.content.global.consumable.Food;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.interaction.DestinationFlag;
import org.areillan.game.interaction.MovementPulse;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.entity.player.ai.random.RandomAITask;
import org.areillan.game.node.item.Item;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.path.Pathfinder;
import org.areillan.game.world.repository.Repository;
import org.areillan.game.world.update.flag.context.ChatMessage;
import org.areillan.game.world.update.flag.player.ChatFlag;
import org.areillan.game.node.entity.player.link.emote.Emotes;
import org.areillan.tools.RandomFunction;

public class RandomAIPActions {
	
	public static List<AIPlayer> random_players = null;
	
	private static final String[] trashMessages = {
			"I'll do your bloody nan in m8",
			"Your sister smells like a tuna sandwich",
			"You suck at bridding bro lmao",
			"Gf your bank :)",
			"Just delete your cache ur bad",
			"FFS mate get wrekt",
			"Dylan is a better pkr than u, kid..l0l"


	};
	
	private static final String[] safeMessages = {
			"Safe up n00b",
			"No safe",
			"Get rekt m8",
			"Stop fkin eating scrub",
			"Dw, you're gunna die anyhow.. :)",
			"Either ur eating shrimps or I'm hitting high asf",
			"Once i kill u wanna rematch?",
			
	};
	
	public static void syncBotThread(final Player player) {
		if (random_players == null || random_players.size() == 0) {
			return;
		}
		for (int aip_index = 0; aip_index < random_players.size(); aip_index++) {
			final AIPlayer bot = random_players.get(aip_index);
			bot.getProperties().setRetaliating(true);
			bot.setAttribute("dead", false);
			GameWorld.submit(new Pulse(1, bot) {
				int sleepTime = 0;
				@Override
				public boolean pulse() {
					if (bot.getAttribute("dead", true)) {
						AIPlayer.deregister(bot.getUid());
						random_players.remove(bot);
						return true;
					}
					if (sleepTime > 0) {
						sleepTime--;
						return false;
					}
					RandomAITask task = bot.getAttribute("aip_randomtask");
					if (task == null) {
						task = RandomAITask.AITask.random();
						bot.setAttribute("aip_randomtask", task);
					}
					sleepTime += doMyTask(bot, task);
					
					return false;
				}
			});
		}
	}
	
	public static void giveStartingItems(AIPlayer bot) {
		bot.getInventory().add(new Item[] {new Item(1265), new Item(1351)});
	}
	
	// We do our task, and return the amount of time we want to sleep for.
	// This should be put into seperate classes but for now will be left basic.
	public static int doMyTask(AIPlayer bot, RandomAITask task) {
		if (task.shouldSwap(bot)) {
			bot.setAttribute("aip_randomtask",RandomAITask.AITask.random());
			return RandomFunction.random(5, 10);
		}
		switch (task) {
		case ROAMING:
			Pathfinder.find(bot, bot.getLocation().transform(RandomFunction.random(-10, 10), RandomFunction.random(-10, 10), 0), false, Pathfinder.SMART).walk(bot);
			return RandomFunction.random(10, 50);
			
		case NOOB_EMOTE:
			//List<Emotes> = bot.getEmoteManager().getEmotes();
			Emotes.DANCE.play(bot);
			return RandomFunction.random(5, 10);
			
		case NOOB_BEG:
			if (task.getTarget() == null || ((Player)task.getTarget()).getLocation().getDistance(bot.getLocation()) > 5) {
				for (Player p1 : Repository.getPlayers()) {
					if (p1 != (Player)bot && p1.getLocation().getDistance(bot.getLocation()) < 5) {
						task.setTarget((Node)p1);
					}
				}
			}
			if (task.getTarget() != null) {
				final AIPlayer botFinal = bot;
				final RandomAITask taskFinal = task;
				bot.getPulseManager().run(new MovementPulse(botFinal, (Player)task.getTarget(), DestinationFlag.FOLLOW_ENTITY) {
					@Override
					public boolean pulse() {
						botFinal.face((Player)taskFinal.getTarget());
						return false;
					}
				}, "movement");
			}
			return RandomFunction.random(15, 30);
			
		case AFKING:
				return RandomFunction.random(20, 50);
			
			// If we get an unsupported task just reroll it.
			default:
				bot.setAttribute("aip_randomtask",RandomAITask.AITask.random());
				break;
		}
		return 0;
	}
	
}
