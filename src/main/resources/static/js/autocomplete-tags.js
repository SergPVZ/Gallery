/**
 * Автодополнение для тегов
 */
class TagAutocomplete {
    constructor(inputId, containerId, hiddenInputId, tags) {
        this.input = document.getElementById(inputId);
        this.container = document.getElementById(containerId);
        this.hiddenInput = document.getElementById(hiddenInputId);
        this.tags = tags || [];
        this.selectedTags = [];

        if (this.input && this.container) {
            this.init();
        }
    }

    init() {
        this.setupAutocomplete();
        this.setupEventListeners();
    }

    setupAutocomplete() {
        // Создаем datalist для автодополнения
        const datalist = document.createElement('datalist');
        datalist.id = 'tag-autocomplete-list';

        this.tags.forEach(tag => {
            const option = document.createElement('option');
            option.value = tag.name;
            option.dataset.id = tag.id;
            datalist.appendChild(option);
        });

        this.input.setAttribute('list', 'tag-autocomplete-list');
        document.body.appendChild(datalist);
    }

    setupEventListeners() {
        this.input.addEventListener('keydown', this.handleKeyDown.bind(this));
        this.input.addEventListener('blur', this.handleBlur.bind(this));
    }

    handleKeyDown(e) {
        if (e.key === 'Enter' || e.key === ',') {
            e.preventDefault();
            this.addTag(this.input.value.trim());
            this.input.value = '';
        }
    }

    handleBlur() {
        const value = this.input.value.trim();
        if (value) {
            this.addTag(value);
            this.input.value = '';
        }
    }

    addTag(tagName) {
        if (!tagName) return;

        // Ищем тег в списке
        const tag = this.tags.find(t =>
            t.name.toLowerCase() === tagName.toLowerCase()
        ) || { name: tagName, id: null };

        // Проверяем, не добавлен ли уже
        if (!this.selectedTags.some(t => t.name.toLowerCase() === tagName.toLowerCase())) {
            this.selectedTags.push(tag);
            this.renderSelectedTags();
        }
    }

    removeTag(tagName) {
        this.selectedTags = this.selectedTags.filter(tag =>
            tag.name.toLowerCase() !== tagName.toLowerCase()
        );
        this.renderSelectedTags();
    }

    renderSelectedTags() {
        this.container.innerHTML = '';

        this.selectedTags.forEach(tag => {
            const tagElement = document.createElement('span');
            tagElement.className = 'selected-tag';
            tagElement.innerHTML = `
                ${tag.name}
                <span class="remove-tag" onclick="tagAutocomplete.removeTag('${tag.name}')">
                    ×
                </span>
            `;
            this.container.appendChild(tagElement);
        });

        // Обновляем скрытое поле
        if (this.hiddenInput) {
            const tagIds = this.selectedTags
                .filter(tag => tag.id)
                .map(tag => tag.id)
                .join(',');
            this.hiddenInput.value = tagIds;
        }
    }

    getSelectedTags() {
        return this.selectedTags;
    }

    clear() {
        this.selectedTags = [];
        this.renderSelectedTags();
        if (this.hiddenInput) {
            this.hiddenInput.value = '';
        }
    }
}

// Глобальный объект для доступа из HTML
let tagAutocomplete = null;

// Функция инициализации, которая будет вызвана из HTML
function initAutocomplete(tags) {
    tagAutocomplete = new TagAutocomplete(
        'tag-input',
        'selected-tags',
        'selected-tag-ids',
        tags
    );
    return tagAutocomplete;
}
