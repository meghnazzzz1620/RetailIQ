import { useState } from "react";
import { askAI } from "../../services/aiService";

function AIAdvisor() {

    const [question, setQuestion] = useState("");
    const [answer, setAnswer] = useState("");
    const [loading, setLoading] = useState(false);

    const handleAskAI = async () => {

        if (!question.trim()) return;

        setLoading(true);

        try {

            const data = await askAI(question);

            setAnswer(data.answer);

        } catch (error) {

            console.error(error);

            setAnswer("Unable to get AI response.");

        }

        setLoading(false);

    };

    return (

        <div className="ai-card">

            <h2>🤖 AI Business Advisor</h2>

            <textarea
                rows="4"
                placeholder="Ask anything about your business..."
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
            />

            <button onClick={handleAskAI} disabled={loading}>

                {loading ? "Thinking..." : "Ask AI"}

            </button>

            {answer && (

                <div className="ai-answer">

                    {answer}

                </div>

            )}

        </div>

    );

}

export default AIAdvisor;