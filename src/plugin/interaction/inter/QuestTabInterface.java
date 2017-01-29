package plugin.interaction.inter;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.content.global.tutorial.TutorialSession;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.diary.AchievementDiary;
import org.areillan.game.node.entity.player.link.diary.DiaryType;
import org.areillan.game.node.entity.player.link.quest.Quest;
import org.areillan.plugin.Plugin;

/**
 * Handles the quest tab action buttons.
 * @author Emperor
 * @author Vexia
 */
public class QuestTabInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(274, this); // Quests
		ComponentDefinition.put(259, this); // Achievement diary
		return this;
	}

	@Override
	public boolean handle(Player p, Component component, int opcode, int button, int slot, int itemId) {
		if (TutorialSession.getExtension(p).getStage() < TutorialSession.MAX_STAGE) {
			return true;
		}
		p.getPulseManager().clear();
		switch (component.getId()) {
		case 274:
			switch (button) {
			case 3:
				p.getAchievementDiaryManager().openTab();
				return true;
			case 10:
				break;
			default:
				Quest quest = p.getQuestRepository().forButtonId(button);
				if (quest != null) {
					p.getInterfaceManager().open(new Component(275));
					quest.drawJournal(p, quest.getStage(p));
					return true;
				}
				return false;
			}
			break;
		case 259:
			switch (button) {
			case 8:
				p.getInterfaceManager().openTab(2, new Component(274));
				return true;
			default:
				AchievementDiary diary = p.getAchievementDiaryManager().getDiary(DiaryType.forChild(button));
				if (diary != null) {
					diary.open(p);
				}
				return true;
			}
		}
		return true;
	}

}